package cn.edu.cess.dto;

import cn.edu.cess.entity.AdminRole;
import cn.edu.cess.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @Author Gnosed Lu
 * @Date 2020/3/27
 * @Description
 */
@Data
public class AdminUserDto {
    private User user;
    private List<AdminRole> roles;
}
