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
@TableName("sheet_contract")
public class SheetContract implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 企业签名
     */
    @TableField("enterprise_sign")
    private String enterpriseSign;

    /**
     * 学校签名
     */
    @TableField("school_sign")
    private String schoolSign;

    /**
     * 学生签名
     */
    @TableField("student_sign")
    private String studentSign;

    /**
     * 企业日期
     */
    @TableField("enterprise_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date enterpriseDate;

    /**
     * 学校日期
     */
    @TableField("school_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date schoolDate;

    /**
     * 学生日期
     */
    @TableField("student_date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date studentDate;

    /**
     * 企业契约
     */
    @TableField("enterprise_contract")
    private String enterpriseContract;

    /**
     * 学校契约
     */
    @TableField("school_contract")
    private String schoolContract;

    /**
     * 学生契约
     */
    @TableField("student_contract")
    private String studentContract;

    /**
     * 企业确认，0未确认，1确认
     */
    @TableField("enterprise_confirm")
    private Boolean enterpriseConfirm;

    /**
     * 学生确认，0未确认，1确认
     */
    @TableField("student_confirm")
    private Boolean studentConfirm;

    /**
     * 学校确认，0未确认，1确认
     */
    @TableField("school_confirm")
    private Boolean schoolConfirm;

    /**
     * 是否失效，0为失效，1为失效
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
