package cn.edu.cess.service.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.AdminPermission;
import cn.edu.cess.entity.AdminRole;
import cn.edu.cess.entity.AdminRolePermission;
import cn.edu.cess.mapper.AdminPermissionMapper;
import cn.edu.cess.mapper.AdminRoleMapper;
import cn.edu.cess.service.IAdminPermissionService;
import cn.edu.cess.service.IAdminRolePermissionService;
import cn.edu.cess.service.IAdminRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-24
 */
@Service
public class AdminPermissionServiceImpl extends ServiceImpl<AdminPermissionMapper, AdminPermission> implements IAdminPermissionService {

    @Autowired
    IAdminRoleService iAdminRoleService;
    @Autowired
    IAdminRolePermissionService iAdminRolePermissionService;

    /**
     * 根据用户名获取权限
     *
     * @param username
     * @return
     */
    @Override
    public Set<String> listPermissionURLsByUser(String username) {
        List<AdminRole> roles = iAdminRoleService.listRoleByUsername(username);
        List<AdminRolePermission> rolePermissions = new ArrayList<>();
        for (AdminRole role : roles) {
            List<AdminRolePermission> adminRolePermissions = iAdminRolePermissionService.listRolePermissionByRid(role.getId());
            for (AdminRolePermission adminRolePermission : adminRolePermissions) {
                rolePermissions.add(adminRolePermission);
            }
        }
        Set<String> pUrls = new HashSet<>();
        QueryWrapper<AdminPermission> queryWrapper;
        for (AdminRolePermission rolePermission : rolePermissions) {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(Constant.ID, rolePermission.getPid());
            List<AdminPermission> permissionList = list(queryWrapper);
            for (AdminPermission adminPermission : permissionList) {
                pUrls.add(adminPermission.getUrl());
            }
            queryWrapper = null;
        }
        return pUrls;
    }

    @Override
    public boolean needFilter(String requestAPI) {
        List<AdminPermission> ps = list();
        for (AdminPermission p : ps) {
            // 这里我们进行前缀匹配，拥有父权限就拥有所有子权限
            if (requestAPI.startsWith(p.getUrl())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<AdminPermission> listPermsByRid(Integer rid) {
        List<AdminRolePermission> adminRolePermissions = iAdminRolePermissionService.listRolePermissionByRid(rid);
        List<AdminPermission> permissionList = new ArrayList<>();
        QueryWrapper<AdminPermission> queryWrapper;
        for (AdminRolePermission adminRolePermission : adminRolePermissions) {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(Constant.ID, adminRolePermission.getPid());
            permissionList.addAll(list(queryWrapper));
        }
        return permissionList;
    }
}
