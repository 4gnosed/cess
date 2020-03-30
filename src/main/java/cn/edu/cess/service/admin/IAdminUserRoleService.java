package cn.edu.cess.service.admin;

import cn.edu.cess.entity.admin.AdminRole;
import cn.edu.cess.entity.admin.AdminUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-23
 */
public interface IAdminUserRoleService extends IService<AdminUserRole> {

    List<AdminUserRole> getUserRoleByUid(int id);

    List<AdminUserRole> getUserRoleByUsername(String username);

    boolean changeUserRole(List<AdminRole> roles, int userId);
}
