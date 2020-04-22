package cn.edu.cess.entity.Vo;

import cn.edu.cess.entity.User;
import lombok.Data;

/**
 * @Author Gnosed Lu
 * @Date 2020/3/27
 * @Description
 */
@Data
public class LoginUserDto {
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private int role;
}
