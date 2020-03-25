package cn.edu.cess.filter;

import cn.edu.cess.service.IAdminPermissionService;
import cn.edu.cess.util.SpringContextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @Author Gnosed Lu
 * @Date 2020/1/16
 */
public class URLPathMatchingFilter extends PathMatchingFilter {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 放行 options 请求
        if (HttpMethod.OPTIONS.toString().equals((httpServletRequest).getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        IAdminPermissionService iAdminPermissionService = SpringContextUtils.getContext().getBean(IAdminPermissionService.class);

        String requestAPI = getPathWithinApplication(request);
        logger.info("访问接口：" + requestAPI);

        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            logger.info("需要登录");
            return false;
        }

        // 判断访问接口是否需要过滤（数据库中是否有对应信息）
        boolean needFilter = iAdminPermissionService.needFilter(requestAPI);
        if (!needFilter) {
            logger.info("接口：" + requestAPI + "无需权限");
            return true;
        } else {
            logger.info("验证访问权限：" + requestAPI);
            // 判断当前用户是否有相应权限
            boolean hasPermission = false;
            String username = subject.getPrincipal().toString();
            Set<String> permissionAPIs = iAdminPermissionService.listPermissionURLsByUser(username);
            for (String api : permissionAPIs) {
                if (api.equals(requestAPI)) {
                    hasPermission = true;
                    break;
                }
            }

            if (hasPermission) {
                logger.info("访问权限：" + requestAPI + "验证成功");
                return true;
            } else {
                logger.info("当前用户没有访问接口" + requestAPI + "的权限");
                return false;
            }
        }
    }
}