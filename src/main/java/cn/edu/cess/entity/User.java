package cn.edu.cess.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {
    @TableId(value = "id")
    int id;
    private String username;
    private String password;
    private String salt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;
    private Boolean enabled;
    private String name;
    private String phone;
    private String email;
    @TableField(exist = false)
    private int roleId;
}
