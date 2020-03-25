package cn.edu.cess.service.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.AdminMenu;
import cn.edu.cess.entity.AdminRoleMenu;
import cn.edu.cess.entity.AdminUserRole;
import cn.edu.cess.mapper.AdminMenuMapper;
import cn.edu.cess.entity.User;
import cn.edu.cess.service.IAdminMenuService;
import cn.edu.cess.service.IAdminRoleMenuService;
import cn.edu.cess.service.IAdminUserRoleService;
import cn.edu.cess.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
public class AdminMenuServiceImpl extends ServiceImpl<AdminMenuMapper, AdminMenu> implements IAdminMenuService {

    @Autowired
    IUserService iUserService;
    @Autowired
    IAdminUserRoleService iAdminUserRoleService;
    @Autowired
    IAdminRoleMenuService iAdminRoleMenuService;

    @Override
    public List<AdminMenu> getMenusByCurrentUser() {
        List<AdminMenu> menus = new ArrayList<>();
        QueryWrapper<AdminMenu> queryWrapper;
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = iUserService.getByName(username);
        List<AdminUserRole> adminUserRoles = iAdminUserRoleService.getUserRoleByUid(user.getId());
        for (AdminUserRole userRole : adminUserRoles) {
            List<AdminRoleMenu> adminRoleMenus = iAdminRoleMenuService.getMenuIdByRid(userRole.getRid());
            for (AdminRoleMenu adminRoleMenu : adminRoleMenus) {
                queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(Constant.ID, adminRoleMenu.getMid());
                List<AdminMenu> menuList = list(queryWrapper);
                for (AdminMenu menu : menuList) {
                    menus.add(menu);
                }
                queryWrapper = null;
            }
        }

        //构建菜单层次树结构关系
        treeMenus(menus);
        return menus;
    }

    /**
     * 首次遍历所有菜单项，根据其parentId再次遍历菜单项，找到id值与该parentId相等的父菜单，
     * 将子菜单放入父菜单，注意不能删除子菜单，因此改子菜单可能是其它菜单的父菜单。
     * 再次遍历所有菜单项，清除树的所有子节点（子菜单），只留下根节点（父菜单）。
     *
     * @param menus
     */
    private void treeMenus(List<AdminMenu> menus) {
        for (AdminMenu subMenu : menus) {
            for (AdminMenu superMenu : menus) {
                if (superMenu.getId() == subMenu.getParentId()) {
                    List<AdminMenu> children = superMenu.getChildren();
                    if (children == null) {
                        children = new ArrayList<>();
                    }
                    children.add(subMenu);
                    superMenu.setChildren(children);
                    break;
                }
            }
        }
        //注意ConcurrentModificationException
        Iterator<AdminMenu> it = menus.iterator();
        while (it.hasNext()){
            AdminMenu menu = it.next();
            if(menu.getParentId()>0){
                it.remove();
            }
        }
    }
}
