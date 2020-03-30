package cn.edu.cess.service.admin.impl;


import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.admin.AdminMenu;
import cn.edu.cess.entity.admin.AdminRole;
import cn.edu.cess.entity.admin.AdminRoleMenu;
import cn.edu.cess.mapper.admin.AdminRoleMenuMapper;
import cn.edu.cess.service.admin.IAdminMenuService;
import cn.edu.cess.service.admin.IAdminRoleMenuService;
import cn.edu.cess.util.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-23
 */
@Service
public class AdminRoleMenuServiceImpl extends ServiceImpl<AdminRoleMenuMapper, AdminRoleMenu> implements IAdminRoleMenuService {

    @Autowired
    IAdminMenuService iAdminMenuService;

    @Override
    public List<AdminRoleMenu> listRoleMenuByRid(Integer rid) {
        QueryWrapper<AdminRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.RID, rid);
        return list(queryWrapper);
    }

    @Override
    public List<AdminMenu> listMenusByRid(Integer rid) {
        List<AdminRoleMenu> adminRoleMenus = listRoleMenuByRid(rid);
        ArrayList<Integer> ids = new ArrayList<>();
        for (AdminRoleMenu adminRoleMenu : adminRoleMenus) {
            ids.add(adminRoleMenu.getMid());
        }
        List<AdminMenu> menus = new ArrayList<>();
        if (ids.isEmpty()) {
            return menus;
        } else {
            menus = iAdminMenuService.listByIds(ids);
            TreeUtil.treeMenus(menus);
            return menus;
        }
    }

    @Override
    public boolean saveRoleMenus(AdminRole role) {
        Integer roleId = role.getId();
        List<AdminMenu> menus = role.getMenus();
        if (menus.isEmpty()) {
            return false;
        }
        QueryWrapper<AdminRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.RID, roleId);
        remove(queryWrapper);
        List<Integer> ids = TreeUtil.recurMenusForIds(menus);
        AdminRoleMenu adminRoleMenu;
        for (Integer menuId : ids) {
            adminRoleMenu = new AdminRoleMenu();
            adminRoleMenu.setRid(roleId);
            adminRoleMenu.setMid(menuId);
            save(adminRoleMenu);
        }
        return true;
    }
}
