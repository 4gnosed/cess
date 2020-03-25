package cn.edu.cess.service;

import cn.edu.cess.entity.AdminUserRole;
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
}
