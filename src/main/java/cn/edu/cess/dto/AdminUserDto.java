package cn.edu.cess.dto;

import cn.edu.cess.entity.User;
import cn.edu.cess.entity.admin.AdminRole;
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
