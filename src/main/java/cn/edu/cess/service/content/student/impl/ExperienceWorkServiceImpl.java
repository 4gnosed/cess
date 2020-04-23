package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.student.ExperienceWork;
import cn.edu.cess.entity.content.student.ResumeWork;
import cn.edu.cess.mapper.content.student.ExperienceWorkMapper;
import cn.edu.cess.service.content.student.IExperienceWorkService;
import cn.edu.cess.service.content.student.IResumeWorkService;
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
 * @since 2020-04-20
 */
@Service
public class ExperienceWorkServiceImpl extends ServiceImpl<ExperienceWorkMapper, ExperienceWork> implements IExperienceWorkService {

    @Autowired
    IResumeWorkService iResumeWorkService;

    @Override
    public ExperienceWork getByResumeId(Integer rid) {
        QueryWrapper<ResumeWork> q = new QueryWrapper<>();
        q.eq(Constant.RID, rid).last("LIMIT 1");
        ResumeWork resumeWork = iResumeWorkService.getOne(q);
        return resumeWork == null ? new ExperienceWork() : getById(resumeWork.getWid());
    }

    @Override
    public void add(Integer rid, ExperienceWork experienceWork) {
        save(experienceWork);
        Integer wid = getByName(experienceWork.getEnterpriseName()).getId();
        ResumeWork resumeWork = new ResumeWork();
        resumeWork.setRid(rid);
        resumeWork.setWid(wid);
        iResumeWorkService.save(resumeWork);
    }

    @Override
    public boolean update(Integer rid, ExperienceWork experienceWork) {
        //验证匹配
        Integer wid = experienceWork.getId();
        Integer wid1 = getByResumeId(rid).getId();
        if (wid1 == null) {
            add(rid, experienceWork);
        } else if (wid1 == wid) {
            UpdateWrapper<ExperienceWork> u = new UpdateWrapper<>();
            u.eq(Constant.ID, wid);
            update(experienceWork, u);
        } else {
            return false;
        }
        return true;
    }


    public ExperienceWork getByName(String name) {
        QueryWrapper<ExperienceWork> q = new QueryWrapper<>();
        q.eq(Constant.ENTERPRISE_NAME, name);
        return getOne(q);
    }
}
