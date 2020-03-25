package cn.edu.cess.interceptor;

import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.result.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomInterceptor extends AbstractClass implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //原生地通过url拦截
//        String url = request.getRequestURI();
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        String contextPath = session.getServletContext().getContextPath();
//        //是否拦截该url
//        if (LoginInterceptorUtil.check(url,contextPath)) {
//            //用户未登录,重定向到登录页
//            if (user == null) {
//                response.sendRedirect("login");
//                return false;
//            }
//        }
//        return true;

        //为解决跨域问题，放行 options 请求，否则无法让前端带上自定义的 header 信息，导致 sessionID 改变，shiro 验证失败
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }
        String requestURI = request.getRequestURI();
        //使用 shiro 验证
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            String message = "身份认证失败";
            logger.error(message);
            ResultFactory.returnUnauthorizedJson(response, message);
            return false;
        }
        String message = "身份认证通过";
        logger.info(message);
        return true;
    }

}
