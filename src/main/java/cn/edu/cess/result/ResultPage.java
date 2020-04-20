package cn.edu.cess.result;

import lombok.Data;

import java.util.Collection;
import java.util.List;

/**
 * @Author Gnosed Lu
 * @Date 2020/3/30
 * @Description
 */
@Data
public class ResultPage {
    private Long total;
    private Collection<?> data;
}
