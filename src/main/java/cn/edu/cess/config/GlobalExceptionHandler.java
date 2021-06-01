package cn.edu.cess.config;


import cn.edu.cess.exception.CustomException;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultCode;
import cn.edu.cess.result.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handleOtherExceptions(Exception e) {
        log.info("Exception e:{}", e);
        if (e instanceof CustomException) {
            return ResultFactory.buildResult(ResultCode.FAIL, ((CustomException) e).getMessageContent(), "");
        } else {
            return ResultFactory.buildFailResult(e.getMessage());
        }
    }


}
