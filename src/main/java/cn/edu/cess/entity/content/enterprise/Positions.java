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
 * @since 2020-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("positions")
public class Positions implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 职位名称
     */
    @TableField("name")
    private String name;

    /**
     * 工作地址
     */
    @TableField("address")
    private String address;

    /**
     * 年薪id
     */
    @TableField("salary_id")
    private Integer salaryId;

    /**
     * 年薪
     */
    @TableField(exist = false)
    private Salary salary;

    /**
     * 学历id
     */
    @TableField("degree_id")
    private Integer degreeId;

    /**
     * 学历
     */
    @TableField(exist = false)
    private Degree degree;

    /**
     * 经验要求id
     */
    @TableField("experience_id")
    private Integer experienceId;

    /**
     * 经验要求
     */
    @TableField(exist = false)
    private Experience experience;

    /**
     * 职位描述
     */
    @TableField("description")
    private String description;

    /**
     * 工作性质id
     */
    @TableField("nature_id")
    private Integer natureId;

    /**
     * 工作性质
     */
    @TableField(exist = false)
    private Nature nature;

    /**
     * 招聘人数
     */
    @TableField("number")
    private Integer number;

    /**
     * 关键词
     */
    @TableField("keyword")
    private String keyword;

    /**
     * 更新日期
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dds")
    private Date updateTime;

    /**
     * 审核状态，0未通过，1通过
     */
    @TableField("enabled")
    private boolean enabled;
}
