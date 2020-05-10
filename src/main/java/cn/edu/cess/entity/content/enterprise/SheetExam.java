package cn.edu.cess.entity.content.enterprise;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalTime;
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
@TableName("sheet_exam")
public class SheetExam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 笔试内容
     */
    @TableField("content")
    private String content;

    /**
     * 笔试成绩
     */
    @TableField("score")
    private Double score;

    /**
     * 笔试日期
     */
    @TableField("date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    /**
     * 笔试时间
     */
    @TableField("time")
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date time;

    /**
     * 笔试时长，已分钟为单位
     */
    @TableField("duration")
    private Integer duration;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
