package cn.edu.cess.util;

import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * 匹配：
     * 第一种情况：连续字符（ascci字典序）
     * 第二种情况：所有字符相同
     *
     * @param str
     * @return 只要满足一种情况，返回true，否则返回false
     */
    public static boolean matchStr(String str) {
        if (str == null || StringUtil.isEmpty(str) || str.length() == 1) {
            return false;
        }
        if (str.length() == 2) {
            return str.charAt(0) + 1 == str.charAt(1) || str.charAt(0) == str.charAt(1);
        }
        boolean continueStr = true;
        boolean equalsStr = true;
        for (int i = 0; i < str.length() - 1; i++) {
            char c = str.charAt(i);
            char next = str.charAt(i + 1);
            if (continueStr && c + 1 != next) {
                continueStr = false;
            }
            if (equalsStr && c != next) {
                equalsStr = false;
            }
        }
        return continueStr || equalsStr;
    }


    /**
     * 解析mapper多个路径返回 Resource数组
     * @param mapperLocation classpath*:mapper/*.xml  有个英文,分隔开
     * @return
     */
    public static Resource[] resolveMapperLocations(String mapperLocation) {
        PathMatchingResourcePatternResolver resolve = new PathMatchingResourcePatternResolver();
        List<String> locations = Arrays.asList(mapperLocation.split(","));
        List<Resource> resp = new ArrayList<>();
        locations.parallelStream().forEach(location -> {
            try {
                resp.addAll(Arrays.asList(resolve.getResources(location)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return resp.toArray(new Resource[0]);
    }
}
