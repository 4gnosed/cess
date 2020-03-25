package cn.edu.cess.service.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.AdminRolePermission;
import cn.edu.cess.mapper.AdminRolePermissionMapper;
import cn.edu.cess.service.IAdminRolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-24
 */
@Service
public class AdminRolePermissionServiceImpl extends ServiceImpl<AdminRolePermissionMapper, AdminRolePermission> implements IAdminRolePermissionService {

    @Override
    public List<AdminRolePermission> listRolePermissionByRid(Integer id) {
        QueryWrapper<AdminRolePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.RID, id);
        return list(queryWrapper);
    }
}
