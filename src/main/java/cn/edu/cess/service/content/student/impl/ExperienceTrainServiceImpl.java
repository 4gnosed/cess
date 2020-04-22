package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.student.ExperienceTrain;
import cn.edu.cess.entity.content.student.ResumeTrain;
import cn.edu.cess.mapper.content.student.ExperienceTrainMapper;
import cn.edu.cess.service.content.student.IExperienceTrainService;
import cn.edu.cess.service.content.student.IResumeTrainService;
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
public class ExperienceTrainServiceImpl extends ServiceImpl<ExperienceTrainMapper, ExperienceTrain> implements IExperienceTrainService {

    @Autowired
    IResumeTrainService iResumeTrainService;

    @Override
    public ExperienceTrain getByResumeId(Integer rid) {
        QueryWrapper<ResumeTrain> q = new QueryWrapper<>();
        q.eq(Constant.RID, rid);
        ResumeTrain resumeTrain = iResumeTrainService.getOne(q);
        return resumeTrain == null ? new ExperienceTrain() : getById(resumeTrain.getTid());
    }

    @Override
    public void add(Integer rid, ExperienceTrain experienceTrain) {
        save(experienceTrain);
        Integer tid = getByOrganization(experienceTrain.getOrganization()).getId();
        ResumeTrain resumeTrain = new ResumeTrain();
        resumeTrain.setRid(rid);
        resumeTrain.setTid(tid);
        iResumeTrainService.save(resumeTrain);
    }

    @Override
    public boolean update(Integer rid, ExperienceTrain experienceTrain) {
        Integer tid = experienceTrain.getId();
        Integer tid1 = getByResumeId(rid).getId();
        if (tid1 == null) {
            add(rid, experienceTrain);
        } else if (tid1 == tid) {
            UpdateWrapper<ExperienceTrain> u = new UpdateWrapper<>();
            u.eq(Constant.ID, tid);
            saveOrUpdate(experienceTrain, u);
        } else {
            return false;
        }
        return true;
    }

    private ExperienceTrain getByOrganization(String organization) {
        QueryWrapper<ExperienceTrain> q = new QueryWrapper<>();
        q.eq(Constant.ORGANIZATION, organization);
        return getOne(q);
    }
}
