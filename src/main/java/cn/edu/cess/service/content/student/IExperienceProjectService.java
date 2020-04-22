package cn.edu.cess.service.content.student;

import cn.edu.cess.entity.content.student.ExperienceProject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-20
 */
public interface IExperienceProjectService extends IService<ExperienceProject> {

    ExperienceProject getByResumeId(Integer rid);

    void add(Integer rid, ExperienceProject experienceProject);

    boolean update(Integer rid, ExperienceProject experienceProject);
}
