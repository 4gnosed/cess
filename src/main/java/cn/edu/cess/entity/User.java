package cn.edu.cess.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("user")
public class User {
    @TableId(value = "id")
    int id;
    private String username;
    private String password;
    private String salt;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;
    private boolean enabled;
    private String name;
    private String phone;
    private String email;
}
