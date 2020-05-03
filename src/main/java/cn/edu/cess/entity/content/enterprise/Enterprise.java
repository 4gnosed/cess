package cn.edu.cess.entity.content.enterprise;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("enterprise")
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * userId
     */
    @TableField(exist = false)
    private Integer userId;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 企业名称
     */
    @TableField("name")
    private String name;

    /**
     * 成立日期
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    /**
     * 所属行业
     */
    @TableField("industry")
    private String industry;

    /**
     * 企业性质
     */
    @TableField("nature")
    private String nature;

    /**
     * 经营范围
     */
    @TableField("scope")
    private String scope;

    /**
     * 融资阶段id
     */
    @TableField("finance_id")
    private Integer financeId;

    /**
     * 融资阶段
     */
    @TableField(exist = false)
    private Finance finance;

    /**
     * 企业规模id
     */
    @TableField("scale_id")
    private Integer scaleId;

    /**
     * 企业规模
     */
    @TableField(exist = false)
    private Scale scale;

    /**
     * 企业地址
     */
    @TableField("address")
    private String address;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 法人代表
     */
    @TableField("boss")
    private String boss;

    /**
     * 企业官网
     */
    @TableField("website")
    private String website;

    /**
     * 企业简介
     */
    @TableField("introduction")
    private String introduction;

    /**
     * 产品介绍
     */
    @TableField("product")
    private String product;

    /**
     * 企业荣誉
     */
    @TableField("honor")
    private String honor;

    /**
     * 企业文化
     */
    @TableField("culture")
    private String culture;

    /**
     * 企业展望
     */
    @TableField("expectation")
    private String expectation;

    /**
     * 福利
     */
    @TableField("welfare")
    private String welfare;

    /**
     * 附件名称
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 宣讲会日期
     */
    @TableField("talk_date")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date talkDate;

    /**
     * 宣讲会时间段id
     */
    @TableField("talk_time_id")
    private Integer talkTimeId;

}
