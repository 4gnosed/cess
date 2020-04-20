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
 * @since 2020-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("resume")
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 实习经验id
     */
    @TableField("work_experience_id")
    private Integer workExperienceId;

    /**
     * 自我评价
     */
    @TableField("self_evaluation")
    private String selfEvaluation;

    /**
     * 备注信息
     */
    @TableField("remark")
    private String remark;

    /**
     * 头像路径
     */
    @TableField("avatar_path")
    private String avatarPath;

    /**
     * 附件路径
     */
    @TableField("file_path")
    private String filePath;


}
