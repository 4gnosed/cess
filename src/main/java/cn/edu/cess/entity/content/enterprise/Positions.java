package cn.edu.cess.entity.content.enterprise;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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

    /**
     * 创建该职位的用户id
     */
    @TableField(exist = false)
    private Integer userId;

    /**
     * 所属企业的id
     */
    @TableField(exist = false)
    private Integer enterpriseId;

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
     * 学历id
     */
    @TableField("degree_id")
    private Integer degreeId;

    /**
     * 经验要求id
     */
    @TableField("experience_id")
    private Integer experienceId;

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 审核状态，0未通过，1通过
     */
    @TableField("enabled")
    private boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Positions positions = (Positions) o;
        return id.equals(positions.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
