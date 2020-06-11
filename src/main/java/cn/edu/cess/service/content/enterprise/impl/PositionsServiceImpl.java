package cn.edu.cess.service.content.enterprise.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.Enterprise;
import cn.edu.cess.entity.content.enterprise.EnterprisePositions;
import cn.edu.cess.entity.content.enterprise.Positions;
import cn.edu.cess.entity.content.enterprise.UserEnterprise;
import cn.edu.cess.mapper.content.enterprise.PositionsMapper;
import cn.edu.cess.result.ResultPage;
import cn.edu.cess.service.content.enterprise.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    IEnterpriseService iEnterpriseService;
    @Autowired
    ISalaryService iSalaryService;

    @Override
    public void savePosition(Positions position) {
        Integer userId = position.getUserId();
        Integer eid = iUserEnterpriseService.getByUid(userId).getEid();
        save(position);
        QueryWrapper<Positions> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.DESCRIPTION, position.getDescription()).last("LIMIT 1");
        Integer pid = getOne(queryWrapper).getId();
        EnterprisePositions enterprisePositions = new EnterprisePositions();
        enterprisePositions.setEid(eid);
        enterprisePositions.setPid(pid);
        iEnterprisePositionsService.save(enterprisePositions);
    }

    @Override
    public ResultPage getByPage(Integer page, Integer size, String keywords,
                                Integer experienceId, Integer degreeId, Integer salaryId, Integer financeId, Integer scaleId) {
        //公司相关职位数量
        int entPosNumber = size / 5;
        //返回的职位集合
        Set<Positions> positionsSet = new HashSet<Positions>();

        // 过滤——公司相关
        QueryWrapper<Enterprise> eQueryWrapper = new QueryWrapper<>();
        QueryWrapper<EnterprisePositions> epQueryWrapper = new QueryWrapper<>();
        if (financeId != null) {
            eQueryWrapper.eq(Constant.FINANCE_ID, financeId);
        }
        if (scaleId != null) {
            eQueryWrapper.eq(Constant.SCALE_ID, scaleId);
        }
        if (financeId != null || scaleId != null) {
            List<Enterprise> enterpriseList = iEnterpriseService.list(eQueryWrapper);
            List<Integer> eIds = new ArrayList<>();
            for (Enterprise enterprise : enterpriseList) {
                eIds.add(enterprise.getId());
            }
            List<EnterprisePositions> enterprisePositions = new ArrayList<>();
            for (Integer eId : eIds) {
                epQueryWrapper.clear();
                epQueryWrapper.eq(Constant.EID, eId).last("LIMIT 1");
                enterprisePositions.add(iEnterprisePositionsService.getOne(epQueryWrapper));
            }
            ArrayList<Integer> pIds = new ArrayList<>();
            for (EnterprisePositions enterprisePosition : enterprisePositions) {
                pIds.add(enterprisePosition.getPid());
            }
            //控制数量，为size的五分之一,取pIds前若干个对应的职位
            for (int i = 0; i < entPosNumber; i++) {
                positionsSet.add(getById(pIds.get(i)));
            }
        }

        // 过滤——职位相关
        QueryWrapper<Positions> pQueryWrapper = new QueryWrapper<>();
        if (experienceId != null) {
            pQueryWrapper.eq(Constant.EXPERIENCE_ID, experienceId);
            positionsFilter(positionsSet, Constant.EXPERIENCE_ID, experienceId);
        }
        if (degreeId != null) {
            pQueryWrapper.eq(Constant.DEGREE_ID, degreeId);
            positionsFilter(positionsSet, Constant.DEGREE_ID, degreeId);
        }
        if (salaryId != null) {
            pQueryWrapper.eq(Constant.SALARY_ID, salaryId);
            positionsFilter(positionsSet, Constant.SALARY_ID, salaryId);
        }

        if (keywords != null && keywords != "") {
            pQueryWrapper.like(Constant.NAME, keywords).or().like(Constant.KEYWORD, keywords);
        }

        Page<Positions> posPage = page(new Page<>(page, size - positionsSet.size()), pQueryWrapper);
        positionsSet.addAll(posPage.getRecords());

//        for (Positions pos : positionsSet) {
//            Integer eid = getEnterpriseId(epQueryWrapper, pos.getId());
//            //职位(企业)所属用户id
//            pos.setEnterpriseId(eid);
//            //职位所属企业id
//            pos.setUserId(iUserEnterpriseService.getByEid(eid).getUid());
//        }
        fillData(positionsSet, epQueryWrapper);
        ResultPage resultPage = new ResultPage();
        resultPage.setData(positionsSet);
        resultPage.setTotal(posPage.getTotal());
        return resultPage;
    }

    @Override
    public void fillData(Collection<Positions> positionsCollection, QueryWrapper<EnterprisePositions> epQueryWrapper) {
        if (epQueryWrapper == null) {
            epQueryWrapper = new QueryWrapper<EnterprisePositions>();
        }
        for (Positions positions : positionsCollection) {
            fillData(positions, epQueryWrapper);
        }
    }

    @Override
    public Enterprise getEnterpriseByPid(Integer pid) {
        QueryWrapper<EnterprisePositions> q = new QueryWrapper<>();
        q.eq(Constant.PID, pid).last("LIMIT 1");
        EnterprisePositions enterprisePositions = iEnterprisePositionsService.getOne(q);
        Enterprise enterprise = iEnterpriseService.getById(enterprisePositions.getEid());
        iEnterpriseService.fillData(enterprise);
        return enterprise;
    }

    @Override
    public void fillData(Positions positions, QueryWrapper<EnterprisePositions> epQueryWrapper) {
        Integer eid = getEnterpriseId(epQueryWrapper, positions.getId());
        //职位(企业)所属用户id
        positions.setEnterpriseId(eid);
        //职位所属企业id
        positions.setUserId(iUserEnterpriseService.getByEid(eid).getUid());
    }

    @Override
    public void fillData(Positions positions) {
        Integer eid = getEnterpriseId(new QueryWrapper<EnterprisePositions>(), positions.getId());
        //职位(企业)
        positions.setEnterpriseId(eid);
        //职位所属企业id
        positions.setUserId(iUserEnterpriseService.getByEid(eid).getUid());
        //年薪
        positions.setSalary(iSalaryService.getById(positions.getSalaryId()));
    }

    public Integer getEnterpriseId(QueryWrapper<EnterprisePositions> epQueryWrapper, Integer positionId) {
        epQueryWrapper.clear();
        epQueryWrapper.eq(Constant.PID, positionId).last("LIMIT 1");
        return iEnterprisePositionsService.getOne(epQueryWrapper).getEid();
    }

    @Override
    public Integer getUserIdByPid(Integer positionId) {
        Integer eid = getEnterpriseId(new QueryWrapper<EnterprisePositions>(), positionId);
        Integer uid = iUserEnterpriseService.getByEid(eid).getUid();
        return uid;
    }

    @Override
    public ResultPage getByPage(Integer page, Integer size, Integer eid) {
        ArrayList<Positions> positions = new ArrayList<>();
        QueryWrapper<EnterprisePositions> epQueryWrapper = new QueryWrapper<>();
        epQueryWrapper.eq(Constant.EID, eid);
        List<EnterprisePositions> enterprisePositionsList = iEnterprisePositionsService.list(epQueryWrapper);
        //手动获取当前页记录
        int index = (page - 1) * size;
        Long length = Long.valueOf(page * size);
        Long total = Long.valueOf(enterprisePositionsList.size());
        if (length > total) {
            length = total;
        }
        for (; index < length; index++) {
            Positions pos = getById(enterprisePositionsList.get(index).getPid());
            pos.setUserId(iUserEnterpriseService.getByEid(eid).getUid());
            positions.add(pos);
        }
        ResultPage resultPage = new ResultPage();
        resultPage.setData(positions);
        resultPage.setTotal(total);
        return resultPage;
    }

    public Set<Positions> positionsFilter(Set<Positions> positionsSet, String column, Integer value) {
        if (column.equals(Constant.EXPERIENCE_ID)) {
            return positionsSet.stream().filter(positions -> positions.getExperienceId() == value).collect(Collectors.toSet());
        } else if (column.equals(Constant.DEGREE_ID)) {
            return positionsSet.stream().filter(positions -> positions.getDegreeId() == value).collect(Collectors.toSet());
        } else if (column.equals(Constant.SALARY_ID)) {
            return positionsSet.stream().filter(positions -> positions.getSalaryId() == value).collect(Collectors.toSet());
        }
        return positionsSet;
    }

    @Override
    public List<Positions> getPositionsListByEid(Integer eid) {
        ArrayList<Positions> positionsList = new ArrayList<>();
        QueryWrapper<EnterprisePositions> q = new QueryWrapper<>();
        q.eq(Constant.EID, eid);
        List<EnterprisePositions> enterprisePositions = iEnterprisePositionsService.list(q);
        for (EnterprisePositions enterprisePosition : enterprisePositions) {
            Positions positions = getById(enterprisePosition.getPid());
            positionsList.add(positions);
        }
        return positionsList;
    }

    @Override
    public List<Positions> getPositionsListByUid(Integer userId) {
        UserEnterprise userEnterprise = iUserEnterpriseService.getByUid(userId);
        return getPositionsListByEid(userEnterprise.getEid());
    }
}
