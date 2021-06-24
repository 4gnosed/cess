package cn.edu.cess.aop;

import cn.edu.cess.config.datasource.dynamic.DataSourceType;
import cn.edu.cess.config.datasource.dynamic.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Package: cn.edu.cess.aop
 * @Description:动态切换数据源实现，拦截被DataSource注解修饰的方法，切换指定数据源
 * @Author: LuDeSong
 * @Date: 2021-6-23 20:35
 */

@Aspect
@Component
@Order(2)
public class DynamicDataSourceAop {

    @Before("@annotation(cn.edu.cess.config.datasource.dynamic.DataSourceType)")
    public void beforeSwitchDS(JoinPoint point) {
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DEFAULT_DS;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@DataSource注解
            if (method.isAnnotationPresent(DataSourceType.class)) {
                DataSourceType annotation = method.getAnnotation(DataSourceType.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 切换数据源
        DataSourceContextHolder.setDB(dataSource);
    }

    @After("@annotation(cn.edu.cess.config.datasource.dynamic.DataSourceType)")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceContextHolder.clearDB();
    }
}
