package cn.edu.cess.service.admin;


import cn.edu.cess.entity.admin.AdminMenu;
import cn.edu.cess.entity.admin.AdminRole;
import cn.edu.cess.entity.admin.AdminRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-23
 */
public interface IAdminRoleMenuService extends IService<AdminRoleMenu> {

    List<AdminRoleMenu> listRoleMenuByRid(Integer rid);

    List<AdminMenu> listMenusByRid(Integer id);

    boolean saveRoleMenus(AdminRole role);
}
