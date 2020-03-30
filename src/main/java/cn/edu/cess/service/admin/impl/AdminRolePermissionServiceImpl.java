package cn.edu.cess.service.admin.impl;


import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.admin.AdminPermission;
import cn.edu.cess.entity.admin.AdminRole;
import cn.edu.cess.entity.admin.AdminRolePermission;
import cn.edu.cess.mapper.admin.AdminRolePermissionMapper;
import cn.edu.cess.service.admin.IAdminPermissionService;
import cn.edu.cess.service.admin.IAdminRolePermissionService;
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
 * @since 2020-03-24
 */
@Service
public class AdminRolePermissionServiceImpl extends ServiceImpl<AdminRolePermissionMapper, AdminRolePermission> implements IAdminRolePermissionService {

    @Autowired
    IAdminPermissionService iAdminPermissionService;

    @Override
    public List<AdminRolePermission> listRolePermissionByRid(Integer id) {
        QueryWrapper<AdminRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.RID, id);
        return list(queryWrapper);
    }

    @Override
    public boolean saveRolePerms(AdminRole role) {
        QueryWrapper<AdminRolePermission> queryWrapper = new QueryWrapper<>();
        Integer roleId = role.getId();
        queryWrapper.eq(Constant.RID, roleId);
        remove(queryWrapper);
        List<AdminPermission> perms = role.getPerms();
        if(perms.isEmpty()){
            return false;
        }
        ArrayList<Integer> ids = new ArrayList<>();
        for (AdminPermission permission : perms) {
            ids.add(permission.getId());
        }
        List<AdminPermission> permissionList = iAdminPermissionService.listByIds(ids);
        AdminRolePermission adminRolePermission;
        for (AdminPermission permission : permissionList) {
            adminRolePermission = new AdminRolePermission();
            adminRolePermission.setRid(roleId);
            adminRolePermission.setPid(permission.getId());
            saveOrUpdate(adminRolePermission);
            adminRolePermission = null;
        }
        return true;
    }
}
