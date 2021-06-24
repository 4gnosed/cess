package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.config.datasource.druid.DruidConfigCluster;
import cn.edu.cess.config.datasource.dynamic.DataSourceType;
import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.Vo.ContractVo;
import cn.edu.cess.entity.content.enterprise.Enterprise;
import cn.edu.cess.entity.content.enterprise.Positions;
import cn.edu.cess.entity.content.enterprise.SheetContract;
import cn.edu.cess.entity.content.student.ResumePositions;
import cn.edu.cess.entity.content.student.Student;
import cn.edu.cess.entity.content.student.UserResume;
import cn.edu.cess.entity.content.student.UserStudent;
import cn.edu.cess.mapper.content.student.ResumePositionsMapper;
import cn.edu.cess.result.ResultPage;
import cn.edu.cess.service.content.enterprise.IPositionsService;
import cn.edu.cess.service.content.enterprise.ISheetContractService;
import cn.edu.cess.service.content.student.IResumePositionsService;
import cn.edu.cess.service.content.student.IStudentService;
import cn.edu.cess.service.content.student.IUserResumeService;
import cn.edu.cess.service.content.student.IUserStudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @since 2020-04-22
 */
@Service
public class ResumePositionsServiceImpl extends ServiceImpl<ResumePositionsMapper, ResumePositions> implements IResumePositionsService {

    @Autowired
    ISheetContractService iSheetContractService;
    @Autowired
    IPositionsService iPositionsService;
    @Autowired
    IUserResumeService iUserResumeService;
    @Autowired
    IUserStudentService iUserStudentService;
    @Autowired
    IStudentService iStudentService;

    @Override
    public boolean saveResumePositions(Integer positionId, Integer rid) {
        QueryWrapper<ResumePositions> q = new QueryWrapper<>();
        q.eq(Constant.RID, rid).eq(Constant.PID, positionId);
        //重复（是否投递过）
        if (getOne(q) == null) {
            ResumePositions resumePositions = new ResumePositions();
            resumePositions.setRid(rid);
            resumePositions.setPid(positionId);
            resumePositions.setStateId(Constant.NEW_RESUME_STATE_ID);
            save(resumePositions);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ResumePositions> getByPid(Integer pid) {
        QueryWrapper<ResumePositions> q = new QueryWrapper<>();
        q.eq(Constant.PID, pid);
        return list(q);
    }

    @Override
    public List<ResumePositions> getByRid(Integer pid) {
        QueryWrapper<ResumePositions> q = new QueryWrapper<>();
        q.eq(Constant.RID, pid);
        return list(q);
    }

    @Override
    public boolean changeState(Integer rid, Integer pid, Integer stateId) {
        ResumePositions resumePositions = getResumePositions(rid, pid);
        //判断事是否已经填写当前状态对应的消息表
        switch (stateId) {
            case 3:
                if (resumePositions.getSheetExamId() == null) {
                    return false;
                }
                break;
            case 4:
                if (resumePositions.getScoreSheetId() == null) {
                    return false;
                }
                break;
            case 5:
                if (resumePositions.getSheetOfferId() == null) {
                    return false;
                }
                break;
        }
        Integer stateIdSaved = resumePositions.getStateId();
        UpdateWrapper<ResumePositions> u = new UpdateWrapper<>();
        //保存当前状态
        u.eq(Constant.RID, rid).eq(Constant.PID, pid).set(Constant.LAST_STATE_ID, stateIdSaved);
        update(u);
        //保存最新状态
        u.clear();
        u.eq(Constant.RID, rid).eq(Constant.PID, pid).set(Constant.STATE_ID, stateId);
        return update(u);
    }

    @Override
    public boolean deleteResumePositions(Integer rid, Integer pid) {
        return remove(getQueryWrapper(rid, pid));
    }

    @Override
    public boolean setScoreSheetId(Integer rid, Integer pid, Integer scoreSheetId) {
        UpdateWrapper<ResumePositions> u = new UpdateWrapper<>();
        u.eq(Constant.RID, rid).eq(Constant.PID, pid).set(Constant.SCORE_SHEET_ID, scoreSheetId);
        return update(u);
    }

    @Override
    public Integer getScoreIdByRidPid(Integer rid, Integer pid) {
        ResumePositions resumePositions = getResumePositions(rid, pid);
        return resumePositions.getScoreSheetId();
    }

    @Override
    public Integer getOfferIdByRidPid(Integer rid, Integer pid) {
        ResumePositions resumePositions = getResumePositions(rid, pid);
        return resumePositions.getSheetOfferId();
    }

    @Override
    public Integer getExamIdByRidPid(Integer rid, Integer pid) {
        ResumePositions resumePositions = getResumePositions(rid, pid);
        return resumePositions.getSheetExamId();
    }


    @Override
    public Integer getContractIdByRidPid(Integer rid, Integer pid) {
        ResumePositions resumePositions = getResumePositions(rid, pid);
        return resumePositions.getSheetContractId();
    }

    @Override
    public Integer getEmployedIdByRidPid(Integer rid, Integer pid) {
        ResumePositions resumePositions = getResumePositions(rid, pid);
        return resumePositions.getSheetEmployedId();
    }

    @Override
    public ResumePositions getResumePositions(Integer rid, Integer pid) {
        QueryWrapper<ResumePositions> q = getQueryWrapper(rid, pid);
        return getOne(q);
    }

    @Override
    public void setSheetOfferId(Integer rid, Integer pid, Integer sheetOfferId) {
        UpdateWrapper<ResumePositions> u = new UpdateWrapper<>();
        u.eq(Constant.RID, rid).eq(Constant.PID, pid).set(Constant.SHEET_OFFER_ID, sheetOfferId);
        update(u);
    }

    @Override
    public void setSheetExamId(Integer rid, Integer pid, Integer sheetExamId) {
        UpdateWrapper<ResumePositions> u = new UpdateWrapper<>();
        u.eq(Constant.RID, rid).eq(Constant.PID, pid).set(Constant.SHEET_EXAM_ID, sheetExamId);
        update(u);
    }

    @Override
    public void setSheetContractId(Integer rid, Integer pid, Integer sheetContractIId) {
        UpdateWrapper<ResumePositions> u = new UpdateWrapper<>();
        u.eq(Constant.RID, rid).eq(Constant.PID, pid).set(Constant.SHEET_CONTRACT_ID, sheetContractIId);
        update(u);
    }

    @Override
    public void setSheetEmployedId(Integer rid, Integer pid, Integer sheetEmployedId) {
        UpdateWrapper<ResumePositions> u = new UpdateWrapper<>();
        u.eq(Constant.RID, rid).eq(Constant.PID, pid).set(Constant.SHEET_EMPLOYED_ID, sheetEmployedId);
        update(u);
    }

    @Override
    @DataSourceType(DruidConfigCluster.CLUSTER_DATA_SOURCE)
    public ResultPage getContractVosByPage(Integer page, Integer size) {
        QueryWrapper<ResumePositions> q = new QueryWrapper<>();
        q.eq(Constant.STATE_ID, Constant.Contract_STATE_ID).or().eq(Constant.STATE_ID, Constant.EMPLOYED_STATE_ID);
        Page<ResumePositions> rpPage = page(new Page<>(page, size), q);
        List<ResumePositions> resumePositionsList = rpPage.getRecords();

        List<ContractVo> contractVos = new ArrayList<>();
        for (ResumePositions resumePositions : resumePositionsList) {
            ContractVo contractVo = new ContractVo();

            SheetContract sheetContract = iSheetContractService.getById(resumePositions.getSheetContractId());
            contractVo.setSheetContract(sheetContract);

            Positions positions = iPositionsService.getById(resumePositions.getPid());
            iPositionsService.fillData(positions);
            contractVo.setPositions(positions);

            Student student = getStudentByResumeId(resumePositions.getRid());
            contractVo.setStudent(student);

            Enterprise enterprise = iPositionsService.getEnterpriseByPid(resumePositions.getPid());
            contractVo.setEnterprise(enterprise);

            contractVos.add(contractVo);
        }

        ResultPage resultPage = new ResultPage();
        resultPage.setTotal(rpPage.getTotal());
        resultPage.setData(contractVos);
        return resultPage;
    }

    @Override
    public Student getStudentByResumeId(Integer rid) {
        UserResume userResume = iUserResumeService.getByRid(rid);
        UserStudent userStudent = iUserStudentService.getByUid(userResume.getUid());
        Student student = iStudentService.getById(userStudent.getSid());
        iStudentService.fillData(student);
        return student;
    }

    @Override
    public ResumePositions getBySheetContractId(Integer sheetContractId) {
        QueryWrapper<ResumePositions> q = new QueryWrapper<>();
        q.eq(Constant.SHEET_CONTRACT_ID, sheetContractId).last("LIMIT 1");
        return getOne(q);
    }

    private QueryWrapper<ResumePositions> getQueryWrapper(Integer rid, Integer pid) {
        QueryWrapper<ResumePositions> q = new QueryWrapper<>();
        q.eq(Constant.RID, rid).eq(Constant.PID, pid).last("LIMIT 1");
        return q;
    }
}
