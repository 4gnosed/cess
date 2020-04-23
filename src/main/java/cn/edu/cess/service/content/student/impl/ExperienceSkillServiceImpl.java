package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.student.ExperienceSkill;
import cn.edu.cess.entity.content.student.ResumeSkill;
import cn.edu.cess.mapper.content.student.ExperienceSkillMapper;
import cn.edu.cess.service.content.student.IExperienceSkillService;
import cn.edu.cess.service.content.student.IResumeSkillService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2020-04-20
 */
@Service
public class ExperienceSkillServiceImpl extends ServiceImpl<ExperienceSkillMapper, ExperienceSkill> implements IExperienceSkillService {

    @Autowired
    IResumeSkillService iResumeSkillService;

    @Override
    public List<ExperienceSkill> getByResumeId(Integer rid) {
        ArrayList<ExperienceSkill> experienceSkills = new ArrayList<>();
        QueryWrapper<ResumeSkill> q = new QueryWrapper<>();
        q.eq(Constant.RID, rid);
        List<ResumeSkill> resumeSkillList = iResumeSkillService.list(q);
        for (ResumeSkill resumeSkill : resumeSkillList) {
            experienceSkills.add(getById(resumeSkill.getSid()));
        }
        if (experienceSkills.size() == 0) {
            experienceSkills.add(new ExperienceSkill());
        }
        return experienceSkills;
    }

    @Override
    public void add(Integer rid, List<ExperienceSkill> experienceSkillList) {
        ResumeSkill resumeSkill = new ResumeSkill();
        resumeSkill.setRid(rid);
        for (ExperienceSkill experienceSkill : experienceSkillList) {
            save(experienceSkill);
            experienceSkill = getByName(experienceSkill.getName());
            resumeSkill.setSid(experienceSkill.getId());
            iResumeSkillService.save(resumeSkill);
        }
    }

    @Override
    public boolean update(Integer rid, List<ExperienceSkill> experienceSkillList) {
        //清除简历关联的已存在的所有技能
        QueryWrapper<ResumeSkill> q = new QueryWrapper<>();
        q.eq(Constant.RID, rid);
        iResumeSkillService.remove(q);
        //再添加新的技能
        add(rid, experienceSkillList);
        return true;
    }

    private ExperienceSkill getByName(String name) {
        QueryWrapper<ExperienceSkill> q = new QueryWrapper<>();
        q.eq(Constant.NAME, name).last("LIMIT 1");
        return getOne(q);
    }
}
