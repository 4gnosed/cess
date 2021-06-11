package cn.edu.cess.entity.Vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

/**
 * @Author Gnosed Lu
 * @Date 2020/3/27
 * @Description
 */
@Data
public class LoginUserDto {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Length(max = 16, message = "密码不超过30个字符")
    private String password;
    @NotBlank(message = "不能为空")
    private String name;
    @NotBlank(message = "联系电话不能为空")
    @Pattern(regexp = "1[0-9]{10}$", message = "请输入正确格式的手机号码")
    private String phone;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    @Positive(message = "角色不正确")
    private int role;
}
