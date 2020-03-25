package cn.edu.cess.result;

import cn.edu.cess.base.AbstractClass;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author Gnosed Lu
 * @Date 2020/1/16
 */
public class ResultFactory extends AbstractClass {
    public static Result buildSuccessResult(Object data) {
        return buildResult(ResultCode.SUCCESS, "成功", data);
    }

    public static Result buildFailResult(String message) {
        return buildResult(ResultCode.FAIL, message, null);
    }

    public static Result buildResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }

    /**
     * 返回 身份认证失败 消息
     *
     * @param response
     */
    public static void returnUnauthorizedJson(HttpServletResponse response, String message) {
        returnJson(response, ResultCode.UNAUTHORIZED, message);
    }

    public static void returnJson(HttpServletResponse response, int resultCode, String message) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            writer = response.getWriter();
            String json = objectMapper.writeValueAsString(buildResult(resultCode, message, null));
            writer.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
