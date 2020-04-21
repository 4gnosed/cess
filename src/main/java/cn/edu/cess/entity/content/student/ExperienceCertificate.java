package cn.edu.cess.entity.content.student;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
 * @since 2020-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("experience_certificate")
public class ExperienceCertificate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 获得日期
     */
    @TableField("get_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate getDate;

    /**
     * 证书名称
     */
    @TableField("name")
    private String name;

    /**
     * 成绩
     */
    @TableField("result")
    private String result;

    /**
     * 证书描述
     */
    @TableField("description")
    private String description;


}
