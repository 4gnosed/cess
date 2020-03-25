package cn.edu.cess.service;

import cn.edu.cess.entity.AdminPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-24
 */
public interface IAdminPermissionService extends IService<AdminPermission> {
    Set<String>  listPermissionURLsByUser(String username);

    boolean needFilter(String requestAPI);
}
