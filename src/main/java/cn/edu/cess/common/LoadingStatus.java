package cn.edu.cess.common;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @Author Gnosed Lu
 * @Date 2020/4/1
 * @Description 用于导出文件的开始和结束状态标识, response响应的状态为-1（正在加载） ，1（已返回） </>
 */
@Component
@Data
public class LoadingStatus {
    private Integer status = 0;
    private HttpServletResponse response;
}
