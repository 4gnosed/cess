package cn.edu.cess.entity;

import cn.edu.cess.entity.content.student.Student;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    int id;
    private String username;
    private String password;
    private String salt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastLogin;
    private Boolean enabled;
    private String name;
    private String phone;
    private String email;
    @TableField(exist = false)
    private int roleId;
    @TableField(exist = false)
    private String role;
    @TableField(exist = false)
    private boolean rememberMe;
    /**
     * 头像路径
     */
    @TableField("avatar_path")
    private String avatarPath;
}
