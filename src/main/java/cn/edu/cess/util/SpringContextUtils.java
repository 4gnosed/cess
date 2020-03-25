package cn.edu.cess.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author Gnosed Lu
 * @Date 2020/3/25
 * @Description
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext context) throws BeansException {
        SpringContextUtils.context = context;
    }
}
