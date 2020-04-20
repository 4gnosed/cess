package cn.edu.cess.entity.content.student;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("resume_train")
public class ResumeTrain implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    /**
     * 简历id
     */
    @TableField("rid")
    private Integer rid;

    /**
     * 培训经验id
     */
    @TableField("tid")
    private Integer tid;


}
