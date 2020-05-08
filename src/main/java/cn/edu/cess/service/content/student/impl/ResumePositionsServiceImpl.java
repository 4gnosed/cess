package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.student.ResumePositions;
import cn.edu.cess.mapper.content.student.ResumePositionsMapper;
import cn.edu.cess.service.content.enterprise.IPositionsService;
import cn.edu.cess.service.content.student.IResumePositionsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
 * @since 2020-04-22
 */
@Service
public class ResumePositionsServiceImpl extends ServiceImpl<ResumePositionsMapper, ResumePositions> implements IResumePositionsService {

    @Autowired
    IPositionsService iPositionsService;

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
        Integer stateIdSaved = resumePositions.getStateId();
        UpdateWrapper<ResumePositions> u = new UpdateWrapper<>();
        //保存上一个简历状态
        u.eq(Constant.RID, rid).eq(Constant.PID, pid).set(Constant.LAST_STATE_ID, stateIdSaved);
        update(u);
        //保存更新状态
        u.clear();
        u.eq(Constant.RID, rid).eq(Constant.PID, pid).set(Constant.STATE_ID, stateId);
        return update(u);
    }

    @Override
    public boolean deleteResumePostions(Integer rid, Integer pid) {
        return remove(getQueryWrapper(rid, pid));
    }

    @Override
    public boolean setScoreSheetId(Integer rid, Integer pid, Integer scoreSheetId) {
        UpdateWrapper<ResumePositions> u = new UpdateWrapper<>();
        u.eq(Constant.RID, rid).eq(Constant.PID, pid).set(Constant.SCORE_SHEET_ID, scoreSheetId);
        return update(u);
    }

    @Override
    public Integer getSidByRidPid(Integer rid, Integer pid) {
        ResumePositions resumePositions = getResumePositions(rid, pid);
        return resumePositions.getScoreSheetId();
    }

    @Override
    public Integer getOidByRidPid(Integer rid, Integer pid) {
        ResumePositions resumePositions = getResumePositions(rid, pid);
        return resumePositions.getSheetOfferId();
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

    public QueryWrapper<ResumePositions> getQueryWrapper(Integer rid, Integer pid) {
        QueryWrapper<ResumePositions> q = new QueryWrapper<>();
        q.eq(Constant.RID, rid).eq(Constant.PID, pid).last("LIMIT 1");
        return q;
    }
}
