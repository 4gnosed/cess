package cn.edu.cess.entity.content.enterprise;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
 * @since 2020-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sheet_score")
public class ScoreSheet implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<ScoreItem> itemList;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 总评分
     */
    @TableField("total_score")
    private Integer totalScore;

    /**
     * 评语及录用建议
     */
    @TableField("advice")
    private String advice;

    /**
     * 是否通过面试，0未通过，1通过
     */
    @TableField("pass")
    private Boolean pass;

    /**
     * 面试人
     */
    @TableField("interviewer")
    private String interviewer;

    /**
     * 面试日期
     */
    @TableField("idate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date idate;


}
