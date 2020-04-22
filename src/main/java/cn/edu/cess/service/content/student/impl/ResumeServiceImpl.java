package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.Vo.FileUrlVo;
import cn.edu.cess.entity.content.student.*;
import cn.edu.cess.mapper.content.student.ResumeMapper;
import cn.edu.cess.service.content.student.*;
import cn.edu.cess.util.FileUploadUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements IResumeService {

    @Autowired
    IUserResumeService iUserResumeService;
    @Autowired
    IExperienceCertificateService iExperienceCertificateService;
    @Autowired
    IExperienceSkillService iExperienceSkillService;
    @Autowired
    IExperienceTrainService iExperienceTrainService;
    @Autowired
    IExperienceProjectService iExperienceProjectService;
    @Autowired
    IExperienceWorkService iExperienceWorkService;

    @Override
    public void saveFilePath(String filePath, Integer userId) {
        UserResume userResume = getUserResume(userId);
        if (userResume == null) {
            //简历之前未保存简历
            Resume resume = new Resume();
            resume.setFilePath(filePath);
            save(resume);
            resume = getByFilePath(filePath);
            Integer rid = resume.getId();
            saveUserResume(userId, rid);
        } else {
            //之前已经保存过简历（包括只保存附件、只保存非附件信息、都保存两者三种情况），不管附件存在与否，直接更新
            Integer rid = userResume.getRid();
            updateFilePath(filePath, rid);
        }
    }

    public void saveUserResume(Integer userId, Integer rid) {
        UserResume userResume;
        userResume = new UserResume();
        userResume.setUid(userId);
        userResume.setRid(rid);
        userResume.setEnabled(true);
        iUserResumeService.save(userResume);
    }

    public void updateFilePath(String filePath, Integer rid) {
        UpdateWrapper<Resume> resumeUpdateWrapper = new UpdateWrapper<>();
        resumeUpdateWrapper.eq(Constant.ID, rid).set(Constant.FILE_PATH, filePath);
        update(resumeUpdateWrapper);
    }

    public UserResume getUserResume(Integer userId) {
        QueryWrapper<UserResume> urQueryWrapper = new QueryWrapper<>();
        urQueryWrapper.eq(Constant.UID, userId);
        return iUserResumeService.getOne(urQueryWrapper);
    }

    public Resume getPreResumeByUid(Integer userId) {
        UserResume userResume = getUserResume(userId);
        return userResume == null ? null : getById(userResume.getRid());
    }

    public Resume getByFilePath(String filePath) {
        QueryWrapper<Resume> q = new QueryWrapper<>();
        q.eq(Constant.FILE_PATH, filePath);
        return getOne(q);
    }

    @Override
    public FileUrlVo getFileUrlVo(Integer userId, HttpServletRequest request) {
        FileUrlVo fileUrlVo = new FileUrlVo();
        fileUrlVo.setFilePath(getPreResumeByUid(userId).getFilePath());
        fileUrlVo.setIpPort(FileUploadUtil.getIpPort(request));
        fileUrlVo.setPath(fileUrlVo.getPath());
        fileUrlVo.setFileName(fileUrlVo.getFileName());
        return fileUrlVo;
    }

    @Override
    public Resume getCompleteResumeByUid(Integer userId, HttpServletRequest request) {
        Resume resume = getPreResumeByUid(userId);
        FileUrlVo fileUrlVo = new FileUrlVo();
        ExperienceProject experienceProject = new ExperienceProject();
        ExperienceWork experienceWork = new ExperienceWork();
        ExperienceTrain experienceTrain = new ExperienceTrain();
        List<ExperienceCertificate> experienceCertificateList = new ArrayList<>();
        List<ExperienceSkill> experienceSkillList = new ArrayList<>();

        if (resume == null) {
            resume = new Resume();
        } else {
            fileUrlVo = getFileUrlVo(userId, request);
            Integer rid = resume.getId();
            experienceProject = iExperienceProjectService.getByResumeId(rid);
            experienceWork = iExperienceWorkService.getByResumeId(rid);
            experienceTrain = iExperienceTrainService.getByResumeId(rid);
            experienceCertificateList = iExperienceCertificateService.getByResumeId(rid);
            experienceSkillList = iExperienceSkillService.getByResumeId(rid);
        }

        resume.setFileUrlVo(fileUrlVo);
        resume.setExperienceProject(experienceProject);
        resume.setExperienceWork(experienceWork);
        resume.setExperienceTrain(experienceTrain);
        resume.setExperienceCertificateList(experienceCertificateList);
        resume.setExperienceSkillList(experienceSkillList);
        return resume;
    }

    @Override
    public Integer addResume(Integer userId, Resume resume) {
        Resume resumeSaved = getPreResumeByUid(userId);
        Integer rid = null;
        if (resumeSaved != null) {
            //之前已经保存过简历（包括只保存附件、只保存非附件信息、都保存两者三种情况）
            rid = resumeSaved.getId();
            //直接更新更新
            updateFilePath(resumeSaved.getFilePath(), rid);
        } else {
            //之前还未保存过简历
            save(resume);
            rid = getBySelfEvaluation(resume.getSelfEvaluation()).getId();
            saveUserResume(userId, rid);
        }
        //保存简历其它内容
        iExperienceProjectService.add(rid, resume.getExperienceProject());
        iExperienceWorkService.add(rid, resume.getExperienceWork());
        iExperienceTrainService.add(rid, resume.getExperienceTrain());
//        iExperienceCertificateService.add(rid, resume.getExperienceCertificateList());
//        iExperienceSkillService.add(rid, resume.getExperienceSkillList());
        return rid;
    }

    @Override
    public boolean updateResume(Integer userId, Resume resume) {
        //验证用户简历是否匹配
        Resume resumeSaved = getPreResumeByUid(userId);
        if (resumeSaved.getId() != resume.getId()) {
            return false;
        }
        Integer rid = resume.getId();
        UpdateWrapper<Resume> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(Constant.ID, rid)
                .set(Constant.SELF_EVALUATION, resume.getSelfEvaluation())
                .set(Constant.REMARK, resume.getRemark());
        update(updateWrapper);
        //更新其它内容
        boolean b1 = iExperienceProjectService.update(rid, resume.getExperienceProject());
        boolean b2 = iExperienceWorkService.update(rid, resume.getExperienceWork());
        boolean b3 = iExperienceTrainService.update(rid, resume.getExperienceTrain());
//        iExperienceCertificateService.saveOrUpdate(rid, resume.getExperienceCertificateList());
//        iExperienceSkillService.saveOrUpdate(rid, resume.getExperienceSkillList());
        if (b1 && b2 && b3) {
            return true;
        } else {
            return false;
        }
    }

    private Resume getBySelfEvaluation(String selfEvaluation) {
        QueryWrapper<Resume> q = new QueryWrapper<>();
        q.eq(Constant.SELF_EVALUATION, selfEvaluation);
        return getOne(q);
    }

}
