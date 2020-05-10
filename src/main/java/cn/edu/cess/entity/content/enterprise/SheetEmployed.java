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
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sheet_employed")
public class SheetEmployed implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 入职日期
     */
    @TableField("date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    /**
     * 入职准备
     */
    @TableField("preparation")
    private String preparation;

    /**
     * 入职要求
     */
    @TableField("requirements")
    private String requirements;

    /**
     * 入职建议
     */
    @TableField("advice")
    private String advice;

    /**
     * 企业祝语
     */
    @TableField("enterprise_hope")
    private String enterpriseHope;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
