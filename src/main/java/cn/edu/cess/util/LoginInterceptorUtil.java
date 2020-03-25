package cn.edu.cess.util;

import cn.edu.cess.constant.Constant;
import org.apache.commons.lang.StringUtils;

public class LoginInterceptorUtil {

    public static boolean check(String url, String contextPath) {
        boolean flag = false;
        url = StringUtils.remove(url, contextPath + "/");
        for (String page : Constant.REQUIRED_AUTH_PAGE) {
            if (url.startsWith(page)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
