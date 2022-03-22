package cn.edu.cess.entity.content.enterprise;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("positions")
@ApiModel("职位")
@Document(indexName = Positions.POSITIONS_IDX_NAME)
public class Positions implements Serializable {

    public static final String POSITIONS_IDX_NAME = "positions_idx_name";

    private static final long serialVersionUID = 1L;

    /**
     * 创建该职位的用户id
     */
    @ApiModelProperty("创建该职位的用户id")
    @TableField(exist = false)
    private Integer userId;

    /**
     * 所属企业的id
     */
    @ApiModelProperty("所属企业的id")
    @TableField(exist = false)
    private Integer enterpriseId;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    @Id
    @Field(type = FieldType.Integer)
    private Integer id;

    /**
     * 职位名称
     */
    @ApiModelProperty("职位名称")
    @TableField("name")
    @Field(type = FieldType.Text)
    private String name;

    /**
     * 工作地址
     */
    @ApiModelProperty("工作地址")
    @TableField("address")
    @Field(type = FieldType.Text)
    private String address;

    /**
     * 年薪id
     */
    @TableField("salary_id")
    @ApiModelProperty("年薪id")
    @Field(type = FieldType.Integer)
    private Integer salaryId;

    /**
     * 年薪
     */
    @TableField(exist = false)
    @ApiModelProperty("年薪")
    private Salary salary;

    /**
     * 学历id
     */
    @TableField("degree_id")
    @ApiModelProperty("学历id")
    @Field(type = FieldType.Integer)
    private Integer degreeId;

    /**
     * 经验要求id
     */
    @TableField("experience_id")
    @ApiModelProperty("经验要求id")
    @Field(type = FieldType.Integer)
    private Integer experienceId;

    /**
     * 职位描述
     */
    @TableField("description")
    @ApiModelProperty("职位描述")
    @Field(type = FieldType.Text)
    private String description;

    /**
     * 工作性质id
     */
    @TableField("nature_id")
    @ApiModelProperty("工作性质id")
    @Field(type = FieldType.Integer)
    private Integer natureId;

    /**
     * 招聘人数
     */
    @TableField("number")
    @ApiModelProperty("招聘人数")
    @Field(type = FieldType.Integer)
    private Integer number;

    /**
     * 关键词
     */
    @TableField("keyword")
    @ApiModelProperty("关键词")
    @Field(type = FieldType.Text)
    private String keyword;

    /**
     * 更新日期
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("更新日期")
    @Field(type = FieldType.Date, format = DateFormat.date)
    private Date updateTime;

    /**
     * 审核状态，0未通过，1通过
     */
    @TableField("enabled")
    @ApiModelProperty("审核状态，0未通过，1通过")
    @Field(type = FieldType.Boolean)
    private boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Positions positions = (Positions) o;
        return id.equals(positions.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
