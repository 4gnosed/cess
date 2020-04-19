package cn.edu.cess.service.content.enterprise.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.EnterprisePositions;
import cn.edu.cess.entity.content.enterprise.Positions;
import cn.edu.cess.mapper.content.enterprise.PositionsMapper;
import cn.edu.cess.result.ResultPage;
import cn.edu.cess.service.content.enterprise.IEnterprisePositionsService;
import cn.edu.cess.service.content.enterprise.IPositionsService;
import cn.edu.cess.service.content.enterprise.IUserEnterpriseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-18
 */
@Service
public class PositionsServiceImpl extends ServiceImpl<PositionsMapper, Positions> implements IPositionsService {

    @Autowired
    IEnterprisePositionsService iEnterprisePositionsService;
    @Autowired
    IUserEnterpriseService iUserEnterpriseService;

    @Override
    public void savePosition(Positions position) {
        Integer userId = position.getUserId();
        Integer eid = iUserEnterpriseService.getByUid(userId).getEid();
        save(position);
        QueryWrapper<Positions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.NAME, position.getName());
        Integer pid = getOne(queryWrapper).getId();
        EnterprisePositions enterprisePositions = new EnterprisePositions();
        enterprisePositions.setEid(eid);
        enterprisePositions.setPid(pid);
        iEnterprisePositionsService.save(enterprisePositions);
    }

    @Override
    public ResultPage getByPage(Integer page, Integer size, Positions positions) {
        QueryWrapper<Positions> q = new QueryWrapper<>();
        //过滤
        Page<Positions> posPage = page(new Page<>(page, size), q);
        List<Positions> positionsList = posPage.getRecords();
        for (Positions pos : positionsList) {
            QueryWrapper<EnterprisePositions> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(Constant.PID, pos.getId());
            Integer eid = iEnterprisePositionsService.getOne(queryWrapper).getEid();
            //职位(企业)所属用户id
            pos.setEnterpriseId(eid);
            //职位所属企业id
            pos.setUserId(iUserEnterpriseService.getByEid(eid).getUid());
        }
        ResultPage resultPage = new ResultPage();
        resultPage.setData(positionsList);
        resultPage.setTotal(posPage.getTotal());
        return resultPage;
    }
}
