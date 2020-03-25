package cn.edu.cess.service;

import cn.edu.cess.entity.AdminMenu;
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
public interface IAdminMenuService extends IService<AdminMenu> {

    List<AdminMenu> getMenusByCurrentUser();
}
