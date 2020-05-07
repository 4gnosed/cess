package cn.edu.cess.entity.content.enterprise;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("enterprise_place")
public class EnterprisePlace implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 企业id
     */
    @TableField("eid")
    private Integer eid;

    /**
     * 地点id
     */
    @TableField("pid")
    private Integer pid;

    /**
     * 日期
     */
    @TableField("talk_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date talkDate;

    /**
     * 时间段id
     */
    @TableField("talk_time_id")
    private Integer talkTimeId;

    /**
     * 是否过期，0未过期，1过期
     */
    @TableField("expire")
    private Boolean expire;


}
