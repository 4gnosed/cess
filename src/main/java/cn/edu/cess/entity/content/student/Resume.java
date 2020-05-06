package cn.edu.cess.entity.content.student;

import cn.edu.cess.entity.Vo.FileUrlVo;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

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

    /**
     * 简历状态id
     */
    @TableField(exist = false)
    private Integer stateId;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    /**
     * 证书
     */
    @TableField(exist = false)
    List<ExperienceCertificate> experienceCertificateList;

    /**
     * 项目经验
     */
    @TableField(exist = false)
    ExperienceProject experienceProject;

    /**
     * 技能
     */
    @TableField(exist = false)
    List<ExperienceSkill> experienceSkillList;

    /**
     * 培训经历
     */
    @TableField(exist = false)
    ExperienceTrain experienceTrain;

    /**
     * 工作经验
     */
    @TableField(exist = false)
    ExperienceWork experienceWork;

    /**
     * 附件
     */
    @TableField(exist = false)
    FileUrlVo fileUrlVo;
}
