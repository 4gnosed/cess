package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.entity.content.student.ExperienceSkill;
import cn.edu.cess.mapper.content.student.ExperienceSkillMapper;
import cn.edu.cess.service.content.student.IExperienceSkillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public List<ExperienceSkill> getByResumeId(Integer rid) {
        ArrayList<ExperienceSkill> experienceSkills = new ArrayList<>();
        return experienceSkills.size() == 0 ? null : experienceSkills;
    }
}