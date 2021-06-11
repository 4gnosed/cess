package cn.edu.cess.service.admin.impl;


import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.common.User;
import cn.edu.cess.entity.admin.AdminMenu;
import cn.edu.cess.entity.admin.AdminRoleMenu;
import cn.edu.cess.entity.admin.AdminUserRole;
import cn.edu.cess.mapper.admin.AdminMenuMapper;
import cn.edu.cess.service.common.IUserService;
import cn.edu.cess.service.admin.IAdminMenuService;
import cn.edu.cess.service.admin.IAdminRoleMenuService;
import cn.edu.cess.service.admin.IAdminUserRoleService;
import cn.edu.cess.util.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
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
        User user = iUserService.getByUsername(username);
        List<AdminUserRole> adminUserRoles = iAdminUserRoleService.getUserRoleByUid(user.getId());
        for (AdminUserRole userRole : adminUserRoles) {
            List<AdminRoleMenu> adminRoleMenus = iAdminRoleMenuService.listRoleMenuByRid(userRole.getRid());
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
        TreeUtil.treeMenus(menus);
        return menus;
    }
}
