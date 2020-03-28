package cn.edu.cess.service;

import cn.edu.cess.entity.AdminRole;
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
public interface IAdminRoleService extends IService<AdminRole> {

    List<AdminRole> listRoleByUsername(String username);

    boolean changeRoleStatus(AdminRole role);

    boolean saveRole(AdminRole role);

    boolean editRole(AdminRole role);

    List<AdminRole> listRoles();
}
