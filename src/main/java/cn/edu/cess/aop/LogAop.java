package cn.edu.cess.aop;

import cn.edu.cess.exception.CustomException;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.reflect.Method;


@Aspect
@Component
@Order(1)
public class LogAop {
    private static final Logger log = LoggerFactory.getLogger(LogAop.class);

    private static int resMaxLength = 3000;

    // 声明一个切点 里面是 execution表达式
    @Pointcut("execution(public * cn.edu.cess.controller..*.*(..))")
    private void logAspect() {

    }

    /**
     * @param joinPoint
     * @Around是可以同时在所拦截方法的前后执行一段逻辑
     */
    @Around("logAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile || args[i] instanceof BindingResult) {
                //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                continue;
            }
            arguments[i] = args[i];
        }
        String paramter = "";
        if (arguments != null) {
            try {
                paramter = JSONObject.toJSONString(arguments);
            } catch (Exception e) {
                paramter = arguments.toString();
            }
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = signature.getParameterNames();

        long start = System.currentTimeMillis();
        String resJson = "";
        Object result = null;
        log.info("开始调用--------------------------------> {}.{}方法,参数名称:{}参数值:{}", method.getDeclaringClass().getSimpleName(), method.getName(), JSONObject.toJSONString(parameterNames), paramter);
        try {
            result = joinPoint.proceed();
            if (result != null) {
                resJson = JSONObject.toJSONString(result);
                if (resJson.length() > resMaxLength) {
                    resJson = resJson.substring(0, resMaxLength);
                }
            }
            log.info("调用结束--------------------------------> {}.{}方法,耗时:{}ms,返回值:{}", method.getDeclaringClass().getSimpleName(), method.getName(), (System.currentTimeMillis() - start), resJson);
            return result;
        } catch (CustomException ce) {
            log.error("调用结束,异常,CustomException--------------------------------> {}.{}方法,耗时:{}ms", method.getDeclaringClass().getSimpleName(), method.getName(), (System.currentTimeMillis() - start), ce);
            throw ce;
        } catch (Exception e) {
            log.error("调用结束,异常--------------------------------> {}.{}方法,耗时:{}ms", method.getDeclaringClass().getSimpleName(), method.getName(), (System.currentTimeMillis() - start), e);
            throw e;
        }
    }

}