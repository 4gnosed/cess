package cn.edu.cess.entity.admin.department;

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
 * @since 2020-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("notice_enterprise")
public class NoticeEnterprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    /**
     * 通知id
     */
    @TableField("nid")
    private Integer nid;

    /**
     * 企业id
     */
    @TableField("eid")
    private Integer eid;


}
