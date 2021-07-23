package cn.edu.cess.service.content.enterprise.impl;


import cn.edu.cess.config.datasource.MybatisPlusConfig;
import cn.edu.cess.config.datasource.dynamic.DataSourceType;
import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.UserEnterprise;
import cn.edu.cess.mapper.content.enterprise.UserEnterpriseMapper;
import cn.edu.cess.service.content.enterprise.IUserEnterpriseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-09
 */
@Service
public class UserEnterpriseServiceImpl extends ServiceImpl<UserEnterpriseMapper, UserEnterprise> implements IUserEnterpriseService {

    @Override
    @DataSourceType(MybatisPlusConfig.CLUSTER_DATA_SOURCE)
    public List<UserEnterprise> getUserEnterprisesByPage(Integer page, Integer size) {
        Page<UserEnterprise> userEnterprisePage = page(new Page<>(page, size));
        List<UserEnterprise> userEnterpriseList = userEnterprisePage.getRecords();
        return userEnterpriseList;
    }

    @Override
    public void updateStatus(boolean enabled, String id) {
        UpdateWrapper<UserEnterprise> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(Constant.ID, id).set(Constant.ENABLED, enabled);
        update(updateWrapper);
    }

    @Override
    @DataSourceType(MybatisPlusConfig.CLUSTER_DATA_SOURCE)
    public UserEnterprise getByUid(Integer userId) {
        QueryWrapper<UserEnterprise> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.UID, userId);
        return getOne(queryWrapper);
    }

    @Override
    @DataSourceType(MybatisPlusConfig.CLUSTER_DATA_SOURCE)
    public UserEnterprise getByEid(Integer eid) {
        QueryWrapper<UserEnterprise> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.EID, eid);
        return getOne(queryWrapper);
    }

    @Override
    @DataSourceType(MybatisPlusConfig.CLUSTER_DATA_SOURCE)
    public int getTotal() {
        return list().size();
    }

    @Override
    public boolean removeByUid(int userId) {
        QueryWrapper<UserEnterprise> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.UID, userId);
        return remove(queryWrapper);
    }
}
