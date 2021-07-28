package cn.edu.cess.entity.seckill;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-07-22
 */
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Integer id;

    /**
     * 商品ID
     */
    private Integer goodId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 下单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 订单状态，1表示成功，0表示失败
     */
    private Boolean status;


}
