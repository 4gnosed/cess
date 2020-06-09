package cn.edu.cess.service.admin.impl;


import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.admin.AdminRole;
import cn.edu.cess.entity.admin.AdminUserRole;
import cn.edu.cess.mapper.admin.AdminRoleMapper;
import cn.edu.cess.service.admin.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

    @Autowired
    IAdminUserRoleService iAdminUserRoleService;
    @Autowired
    IAdminRolePermissionService iAdminRolePermissionService;
    @Autowired
    IAdminRoleMenuService iAdminRoleMenuService;
    @Autowired
    IAdminPermissionService iAdminPermissionService;

    @Override
    public List<AdminRole> listRoles() {
        List<AdminRole> roles = list();
        for (AdminRole role : roles) {
            role.setPerms(iAdminPermissionService.listPermsByRid(role.getId()));
            role.setMenus(iAdminRoleMenuService.listMenusByRid(role.getId()));
        }
        return roles;
    }

    @Override
    public List<AdminRole> listRoleByUsername(String username) {
        List<AdminUserRole> adminUserRoles = iAdminUserRoleService.getUserRoleByUsername(username);
        ArrayList<AdminRole> roles = new ArrayList<>();
        for (AdminUserRole adminUserRole : adminUserRoles) {
            roles.addAll(listById(adminUserRole.getRid()));
        }
        return roles;
    }

    @Override
    public List<AdminRole> listRoleByUserId(Integer userId) {
        List<AdminUserRole> adminUserRoles = iAdminUserRoleService.getUserRoleByUid(userId);
        ArrayList<AdminRole> roles = new ArrayList<>();
        for (AdminUserRole adminUserRole : adminUserRoles) {
            roles.addAll(listById(adminUserRole.getRid()));
        }
        return roles;
    }

    @Override
    public boolean saveRole(AdminRole role) {
        if (listByName(role.getName()).isEmpty()) {
            role.setEnabled(false);
            save(role);
            return true;
        }
        return false;
    }

    @Override
    public boolean editRole(AdminRole role) {
        updateById(role);
        boolean b1 = iAdminRolePermissionService.saveRolePerms(role);
        boolean b2 = iAdminRoleMenuService.saveRoleMenus(role);
        return enableRoleStatus(role, b1, b2);
    }

    @Override
    public boolean changeRoleStatus(AdminRole role) {
        boolean b1 = iAdminRolePermissionService.listRolePermissionByRid(role.getId()).isEmpty();
        boolean b2 = iAdminRoleMenuService.listRoleMenuByRid(role.getId()).isEmpty();
        return enableRoleStatus(role, !b1, !b2);
    }

    private boolean enableRoleStatus(AdminRole role, boolean hasPerms, boolean hasMenus) {
        UpdateWrapper<AdminRole> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(Constant.ID, role.getId());
        if (hasPerms && hasMenus) {
            updateWrapper.set(Constant.ENABLED, role.getEnabled());
            return update(updateWrapper);
        } else {
            updateWrapper.set(Constant.ENABLED, 0);
            update(updateWrapper);
            return false;
        }
    }

    private List<AdminRole> listById(int id) {
        QueryWrapper<AdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.ID, id);
        return list(queryWrapper);
    }

    private List<AdminRole> listByName(String name) {
        QueryWrapper<AdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.NAME, name);
        return list(queryWrapper);
    }
}
