package cn.edu.cess.entity.Vo;

import cn.edu.cess.entity.common.User;
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
