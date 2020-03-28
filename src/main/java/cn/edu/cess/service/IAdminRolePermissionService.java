package cn.edu.cess.service;

import cn.edu.cess.entity.AdminRole;
import cn.edu.cess.entity.AdminRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-24
 */
public interface IAdminRolePermissionService extends IService<AdminRolePermission> {

    List<AdminRolePermission> listRolePermissionByRid(Integer id);

    boolean saveRolePerms(AdminRole role);
}
