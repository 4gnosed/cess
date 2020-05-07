package cn.edu.cess.entity.content.enterprise;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("place")
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 地点
     */
    @TableField("name")
    private String name;

    /**
     * 8:00-10:00 是时间段否被占用，0未被占用，1已被占用且未过期
     */
    @TableField(exist = false)
    private boolean time1;

    /**
     * 10:00-12:00 是时间段否被占用，0未被占用，1已被占用且未过期
     */
    @TableField(exist = false)
    private boolean time2;

    /**
     * 14:00-16:00 是时间段否被占用，0未被占用，1已被占用且未过期
     */
    @TableField(exist = false)
    private boolean time3;

    /**
     * 16:00-18:00 是时间段否被占用，0未被占用，1已被占用且未过期
     */
    @TableField(exist = false)
    private boolean time4;

    /**
     * 17:00-21:00 是时间段否被占用，0未被占用，1已被占用且未过期
     */
    @TableField(exist = false)
    private boolean time5;

}
