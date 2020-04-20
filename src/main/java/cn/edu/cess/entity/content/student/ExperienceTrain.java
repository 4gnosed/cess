package cn.edu.cess.entity.content.student;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
@TableName("experience_train")
public class ExperienceTrain implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 开始培训日期
     */
    @TableField("begin_date")
    private LocalDate beginDate;

    /**
     * 培训结束日期
     */
    @TableField("end_date")
    private LocalDate endDate;

    /**
     * 培训机构
     */
    @TableField("organization")
    private String organization;

    /**
     * 培训地点
     */
    @TableField("address")
    private String address;

    /**
     * 获得证书
     */
    @TableField("certificate")
    private String certificate;

    /**
     * 专业描述
     */
    @TableField("description")
    private String description;


}
