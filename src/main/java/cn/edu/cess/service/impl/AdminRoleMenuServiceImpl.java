package cn.edu.cess.service.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.AdminRoleMenu;
import cn.edu.cess.mapper.AdminRoleMenuMapper;
import cn.edu.cess.service.IAdminRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public List<AdminRoleMenu> getMenuIdByRid(Integer rid) {
        QueryWrapper<AdminRoleMenu> querywrapper = new QueryWrapper<>();
        querywrapper.eq(Constant.RID, rid);
        return list(querywrapper);
    }
}
