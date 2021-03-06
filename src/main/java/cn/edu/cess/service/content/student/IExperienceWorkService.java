package cn.edu.cess.service.content.student;

import cn.edu.cess.entity.content.student.ExperienceWork;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-20
 */
public interface IExperienceWorkService extends IService<ExperienceWork> {

    ExperienceWork getByResumeId(Integer rid);

    void add(Integer rid, ExperienceWork experienceWork);

    boolean update(Integer rid, ExperienceWork experienceWork);
}
