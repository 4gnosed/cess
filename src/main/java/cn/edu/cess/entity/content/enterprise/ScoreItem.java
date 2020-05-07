package cn.edu.cess.entity.content.enterprise;

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
 * @since 2020-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("score_item")
public class ScoreItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 评分表id
     */
    @TableField("score_sheet_id")
    private Integer scoreSheetId;

    /**
     * 评分项
     */
    @TableField("name")
    private String name;

    /**
     * 面试情况
     */
    @TableField("item_situation")
    private String itemSituation;

    /**
     * 得分
     */
    @TableField("item_score")
    private Integer itemScore;


}
