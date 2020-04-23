package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.student.ExperienceCertificate;
import cn.edu.cess.entity.content.student.ResumeCertificate;
import cn.edu.cess.mapper.content.student.ExperienceCertificateMapper;
import cn.edu.cess.service.content.student.IExperienceCertificateService;
import cn.edu.cess.service.content.student.IResumeCertificateService;
import cn.edu.cess.service.content.student.IResumeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.tomcat.util.bcel.Const;
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
public class ExperienceCertificateServiceImpl extends ServiceImpl<ExperienceCertificateMapper, ExperienceCertificate> implements IExperienceCertificateService {

    @Autowired
    IResumeCertificateService iResumeCertificateService;

    @Override
    public List<ExperienceCertificate> getByResumeId(Integer rid) {
        ArrayList<ExperienceCertificate> experienceCertificateList = new ArrayList<>();
        QueryWrapper<ResumeCertificate> q = new QueryWrapper<>();
        q.eq(Constant.RID, rid);
        List<ResumeCertificate> resumeCertificateList = iResumeCertificateService.list(q);
        for (ResumeCertificate resumeCertificate : resumeCertificateList) {
            experienceCertificateList.add(getById(resumeCertificate.getCid()));
        }
        if (experienceCertificateList.size() == 0) {
            experienceCertificateList.add(new ExperienceCertificate());
        }
        return experienceCertificateList;
    }

    @Override
    public void add(Integer rid, List<ExperienceCertificate> experienceCertificateList) {
        ResumeCertificate resumeCertificate = new ResumeCertificate();
        resumeCertificate.setRid(rid);
        for (ExperienceCertificate experienceCertificate : experienceCertificateList) {
            save(experienceCertificate);
            experienceCertificate = getByName(experienceCertificate.getName());
            resumeCertificate.setCid(experienceCertificate.getId());
            iResumeCertificateService.save(resumeCertificate);
        }
    }

    @Override
    public boolean update(Integer rid, List<ExperienceCertificate> experienceCertificateList) {
        //清除简历关联的已存在的所有证书
        QueryWrapper<ResumeCertificate> q = new QueryWrapper<>();
        q.eq(Constant.RID, rid);
        iResumeCertificateService.remove(q);
        //再添加新的证书
        add(rid, experienceCertificateList);
        return true;
    }

    private ExperienceCertificate getByName(String name) {
        QueryWrapper<ExperienceCertificate> q = new QueryWrapper<>();
        q.eq(Constant.NAME, name).last("LIMIT 1");
        return getOne(q);
    }
}
