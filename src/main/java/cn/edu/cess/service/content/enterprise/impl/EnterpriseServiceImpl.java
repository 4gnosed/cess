package cn.edu.cess.service.content.enterprise.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.Enterprise;
import cn.edu.cess.entity.content.enterprise.Finance;
import cn.edu.cess.entity.content.enterprise.Scale;
import cn.edu.cess.mapper.content.enterprise.EnterpriseMapper;
import cn.edu.cess.service.content.enterprise.IEnterpriseService;
import cn.edu.cess.service.content.enterprise.IFinanceService;
import cn.edu.cess.service.content.enterprise.IScaleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-12
 */
@Service
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements IEnterpriseService {

    @Autowired
    IFinanceService iFinanceService;
    @Autowired
    IScaleService iScaleService;

    @Override
    public void fillData(Enterprise enterprise) {
        Finance finance = iFinanceService.getById(enterprise.getFinanceId());
        Scale scale = iScaleService.getById(enterprise.getScaleId());
        enterprise.setFinance(finance);
        enterprise.setScale(scale);
    }

    @Override
    public void updateEnterprise(Enterprise enterprise) {
        UpdateWrapper<Enterprise> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(Constant.ID, enterprise.getId());
        update(enterprise, updateWrapper);
    }

    @Override
    public Integer getLastId() {
        QueryWrapper<Enterprise> q = new QueryWrapper<>();
        q.orderByDesc(Constant.ID).last("limit 0 , 1");
        return list(q).get(0).getId();
    }

    @Override
    public Enterprise getByName(String name) {
        QueryWrapper<Enterprise> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.NAME,name);
        return getOne(queryWrapper);
    }
}
