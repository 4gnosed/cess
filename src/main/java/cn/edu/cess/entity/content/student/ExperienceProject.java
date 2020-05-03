package cn.edu.cess.entity.content.student;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@TableName("experience_project")
public class ExperienceProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目开始日期
     */
    @TableField("begin_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date beginDate;

    /**
     * 项目结束日期
     */
    @TableField("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endDate;

    /**
     * 项目名称
     */
    @TableField("name")
    private String name;

    /**
     * 开发工具
     */
    @TableField("tool")
    private String tool;

    /**
     * 软件环境
     */
    @TableField("software")
    private String software;

    /**
     * 硬件环境
     */
    @TableField("hardware")
    private String hardware;

    /**
     * 项目人数
     */
    @TableField("number")
    private Integer number;

    /**
     * 项目描述
     */
    @TableField("description")
    private String description;

    /**
     * 责任描述
     */
    @TableField("responsibility")
    private String responsibility;


}
