package cn.edu.cess.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * aop拦截
 */
@Aspect
@Component
@Order(1)
public class CustomAop {
    private static final Logger log = LoggerFactory.getLogger(CustomAop.class);

    // 声明一个切点 里面是 execution表达式
    @Pointcut("execution(public * cn.edu.cess.controller.content.student.*.*(..))||execution(public * cn.edu.cess.controller.content.enterprise.*.*(..))")
    private void controllerAspect() {

    }

    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof MultipartFile) {
                log.info("拦截上传文件接口...");
            }
        }
    }

    // 在方法执行完结后
    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturning(Object o) {

    }

}
