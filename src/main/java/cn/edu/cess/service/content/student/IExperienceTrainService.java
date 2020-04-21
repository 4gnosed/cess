package cn.edu.cess.service.content.student;

import cn.edu.cess.entity.content.student.ExperienceTrain;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-20
 */
public interface IExperienceTrainService extends IService<ExperienceTrain> {

    ExperienceTrain getByResumeId(Integer rid);

    void add(Integer rid, ExperienceTrain experienceTrain);
}
