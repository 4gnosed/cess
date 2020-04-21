package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.student.ExperienceProject;
import cn.edu.cess.entity.content.student.ResumeProject;
import cn.edu.cess.mapper.content.student.ExperienceProjectMapper;
import cn.edu.cess.service.content.student.IExperienceProjectService;
import cn.edu.cess.service.content.student.IResumeProjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class ExperienceProjectServiceImpl extends ServiceImpl<ExperienceProjectMapper, ExperienceProject> implements IExperienceProjectService {

    @Autowired
    IResumeProjectService iResumeProjectService;

    @Override
    public ExperienceProject getByResumeId(Integer rid) {
        QueryWrapper<ResumeProject> rpQueryWrapper = new QueryWrapper<>();
        rpQueryWrapper.eq(Constant.RID, rid);
        ResumeProject resumeProject = iResumeProjectService.getOne(rpQueryWrapper);
        return resumeProject == null ? null : getById(resumeProject.getPid());
    }

    @Override
    public void add(Integer rid, ExperienceProject experienceProject) {
        save(experienceProject);
        Integer pid = getByName(experienceProject.getName()).getId();
        ResumeProject resumeProject = new ResumeProject();
        resumeProject.setRid(rid);
        resumeProject.setPid(pid);
        iResumeProjectService.save(resumeProject);
    }

    public ExperienceProject getByName(String name) {
        QueryWrapper<ExperienceProject> q = new QueryWrapper<>();
        q.eq(Constant.NAME, name);
        return getOne(q);
    }
}
