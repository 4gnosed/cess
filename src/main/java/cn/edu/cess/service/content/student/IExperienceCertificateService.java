package cn.edu.cess.service.content.student;

import cn.edu.cess.entity.content.student.ExperienceCertificate;
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
public interface IExperienceCertificateService extends IService<ExperienceCertificate> {

    List<ExperienceCertificate> getByResumeId(Integer rid);
}
