package cn.edu.cess.entity.content.student;

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
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * userId
     */
    @TableField(exist = false)
    private Integer userId;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学号
     */
    @TableField("student_id")
    private Integer studentId;

    /**
     * 学生姓名
     */
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 出生日期
     */
    @TableField("birthday")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 身份证号
     */
    @TableField("id_card")
    private String idCard;

    /**
     * 民族ID
     * mysql随机产生：update student set nation_id = ROUND(RAND()*55 +1) where id >2000
     */
    @TableField("nation_id")
    private Integer nationId;

    /**
     * 民族
     */
    @TableField(exist = false)
    private Nation nation;

    /**
     * 籍贯
     */
    @TableField("native_place")
    private String nativePlace;

    /**
     * 政治面貌ID
     */
    @TableField("politic_id")
    private Integer politicId;


    /**
     * 政治面貌
     */
    @TableField(exist = false)
    private Politics politics;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 电话号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 联系地址
     */
    @TableField("address")
    private String address;

    /**
     * 最高学历
     */
    @TableField("top_degree")
    private String topDegree;

    /**
     * 毕业院校
     */
    @TableField("school")
    private String school;

    /**
     * 所属学院ID
     */
    @TableField("department_id")
    private Integer departmentId;

    /**
     * 所属学院
     */
    @TableField(exist = false)
    private Department department;

    /**
     * 所属专业ID
     */
    @TableField("specialty_id")
    private Integer specialtyId;

    /**
     * 所属专业
     */
    @TableField(exist = false)
    private Specialty specialty;

    /**
     * 学生职位ID
     * mysql随机产生：update student set positon_id = ROUND(RAND()*6 +1) where id%2 =0 and id <2400
     */
    @TableField("position_id")
    private Integer positionId;

    /**
     * 学生职位
     */
    @TableField(exist = false)
    private Position position;

    /**
     * 语言能力
     */
    @TableField("language_level")
    private String languageLevel;

    /**
     * 计算机能力
     */
    @TableField("computer_level")
    private String computerLevel;

    /**
     * 入学日期
     */
    @TableField("begin_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;

    /**
     * 毕业日期
     */
    @TableField("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;


}
