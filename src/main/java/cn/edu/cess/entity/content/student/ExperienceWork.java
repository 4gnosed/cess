package cn.edu.cess.entity.content.student;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2020-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("experience_work")
public class ExperienceWork implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 开始日期
     */
    @TableField("begin_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date beginDate;

    /**
     * 结束日期
     */
    @TableField("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endDate;

    /**
     * 企业名称
     */
    @TableField("enterprise_name")
    private String enterpriseName;

    /**
     * 所属行业
     */
    @TableField("industry")
    private String industry;

    /**
     * 部门
     */
    @TableField("department")
    private String department;

    /**
     * 实习职位
     */
    @TableField("position")
    private String position;

    /**
     * 企业性质
     */
    @TableField("nature")
    private String nature;

    /**
     * 企业规模id
     */
    @TableField("scale_id")
    private Integer scaleId;

    /**
     * 工作性质id
     */
    @TableField("nature_id")
    private Integer natureId;

    /**
     * 部门人数
     */
    @TableField("department_number")
    private Integer departmentNumber;

    /**
     * 主管
     */
    @TableField("leader")
    private String leader;

    /**
     * 证明人
     */
    @TableField("witness")
    private String witness;

    /**
     * 证明人联系电话
     */
    @TableField("witness_phone")
    private String witnessPhone;

    /**
     * 离职原因
     */
    @TableField("workout_reason")
    private String workoutReason;

    /**
     * 主要成绩
     */
    @TableField("achievement")
    private String achievement;

    /**
     * 工作描述
     */
    @TableField("description")
    private String description;


}
