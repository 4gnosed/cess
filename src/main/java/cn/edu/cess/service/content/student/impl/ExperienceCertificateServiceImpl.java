package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.entity.content.student.ExperienceCertificate;
import cn.edu.cess.mapper.content.student.ExperienceCertificateMapper;
import cn.edu.cess.service.content.student.IExperienceCertificateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-20
 */
@Service
public class ExperienceCertificateServiceImpl extends ServiceImpl<ExperienceCertificateMapper, ExperienceCertificate> implements IExperienceCertificateService {

    @Override
    public List<ExperienceCertificate> getByResumeId(Integer rid) {
        ArrayList<ExperienceCertificate> experienceCertificates = new ArrayList<>();
        return  experienceCertificates.size()==0 ? null : experienceCertificates;
    }
}
