package cn.edu.cess.util;

import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @Package: cn.edu.cess.util
 * @Description:不好命名的工具类
 * @Author: LuDeSong
 * @Date: 2021-6-1 10:31
 */

public class CommonUtil {
    /**
     * 校验参数合法性
     *
     * @param bindingResult
     * @return
     */
    public static Result returnErrorList(BindingResult bindingResult) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder s = new StringBuilder();
        for (ObjectError error : allErrors) {
            s.append(error.getDefaultMessage()).append(" ");
        }
        return ResultFactory.buildFailResult(s.toString());
    }
}
