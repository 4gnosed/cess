package cn.edu.cess.entity.content.student;

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
 * @since 2020-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("resume_positions")
public class ResumePositions implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 简历id
     */
    @TableField("rid")
    private Integer rid;

    /**
     * 职位id
     */
    @TableField("pid")
    private Integer pid;

    /**
     * 简历状态id
     */
    @TableField("state_id")
    private Integer stateId;

    /**
     * offer表id
     */
    @TableField("score_sheet_id")
    private Integer scoreSheetId;

    /**
     * 评分表id
     */
    @TableField("sheet_offer_id")
    private Integer sheetOfferId;

}
