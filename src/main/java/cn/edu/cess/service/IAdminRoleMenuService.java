package cn.edu.cess.service;

import cn.edu.cess.entity.AdminRoleMenu;
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
public interface IAdminRoleMenuService extends IService<AdminRoleMenu> {

    List<AdminRoleMenu> getMenuIdByRid(Integer rid);
}
