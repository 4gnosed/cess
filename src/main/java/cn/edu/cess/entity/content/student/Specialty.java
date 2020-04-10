package cn.edu.cess.entity.content.student;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Objects;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-30
 */
@Data
@Accessors(chain = true)
@TableName("specialty")
public class Specialty implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 专业名称
     */
    @TableField("name")
    private String name;

    /**
     * 专业简介
     */
    @TableField("descp")
    private String descp;

    /**
     * 所属院系
     */
    @TableField("department_id")
    private Integer departmentId;

    public Specialty(String name) {
        this.name = name;
    }

    public Specialty( ) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialty specialty = (Specialty) o;
        return Objects.equals(name, specialty.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
