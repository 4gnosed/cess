package cn.edu.cess.service.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.AdminRole;
import cn.edu.cess.entity.AdminUserRole;
import cn.edu.cess.entity.User;
import cn.edu.cess.mapper.AdminUserRoleMapper;
import cn.edu.cess.service.IAdminUserRoleService;
import cn.edu.cess.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class AdminUserRoleServiceImpl extends ServiceImpl<AdminUserRoleMapper, AdminUserRole> implements IAdminUserRoleService {

    @Autowired
    IUserService iUserService;

    @Override
    public List<AdminUserRole> getUserRoleByUid(int id) {
        QueryWrapper<AdminUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.UID, id);
        return list(queryWrapper);
    }

    @Override
    public List<AdminUserRole> getUserRoleByUsername(String username) {
        User user = iUserService.getByName(username);
        return getUserRoleByUid(user.getId());
    }

    @Transactional
    @Override
    public boolean changeUserRole(List<AdminRole> roles, int userId) {
        QueryWrapper<AdminUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.UID, userId);
        remove(queryWrapper);
        for (AdminRole role : roles) {
            AdminUserRole adminUserRole = new AdminUserRole();
            adminUserRole.setUid(userId);
            adminUserRole.setRid(role.getId());
            save(adminUserRole);
        }
        return true;
    }

}
