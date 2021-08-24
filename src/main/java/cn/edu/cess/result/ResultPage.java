package cn.edu.cess.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

/**
 * @Author Gnosed Lu
 * @Date 2020/3/30
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultPage {
    private Long total;
    private Collection<?> data;
}
