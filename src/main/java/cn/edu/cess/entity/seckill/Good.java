package cn.edu.cess.entity.seckill;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-07-22
 */
@Data
public class Good implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private String goodId;

    /**
     * 商品名称
     */
    private String goodName;

    /**
     * 当前库存
     */
    private Integer stock;

    /**
     * 初始库存
     */
    private Integer oldStock;


}
