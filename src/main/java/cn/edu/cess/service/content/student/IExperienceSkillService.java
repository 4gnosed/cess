package cn.edu.cess.service.content.student;

import cn.edu.cess.entity.content.student.ExperienceSkill;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-20
 */
public interface IExperienceSkillService extends IService<ExperienceSkill> {

    List<ExperienceSkill> getByResumeId(Integer rid);

    void add(Integer rid, List<ExperienceSkill> experienceSkillList);

    boolean update(Integer rid, List<ExperienceSkill> experienceSkillList);
}
