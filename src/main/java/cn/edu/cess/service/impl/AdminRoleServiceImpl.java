package cn.edu.cess.service.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.AdminRole;
import cn.edu.cess.entity.AdminUserRole;
import cn.edu.cess.mapper.AdminRoleMapper;
import cn.edu.cess.service.IAdminRoleService;
import cn.edu.cess.service.IAdminUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-23
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements IAdminRoleService {

    @Autowired
    IAdminUserRoleService iAdminUserRoleService;

    @Override
    public List<AdminRole> listRoleByUsername(String username) {
        List<AdminUserRole> adminUserRoles=iAdminUserRoleService.getUserRoleByUsername(username);
        ArrayList<AdminRole> roles = new ArrayList<>();
        QueryWrapper<AdminRole> queryWrapper ;
        for (AdminUserRole adminUserRole : adminUserRoles) {
            queryWrapper= new QueryWrapper<>();
            queryWrapper.eq(Constant.ID, adminUserRole.getRid());
            List<AdminRole> roleList = list(queryWrapper);
            for (AdminRole role : roleList) {
                roles.add(role);
            }
            queryWrapper=null;
        }
        return roles;
    }
}
