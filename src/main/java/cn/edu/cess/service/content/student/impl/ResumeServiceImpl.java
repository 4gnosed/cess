package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.Vo.FileUrlVo;
import cn.edu.cess.entity.content.student.Resume;
import cn.edu.cess.entity.content.student.UserResume;
import cn.edu.cess.mapper.content.student.ResumeMapper;
import cn.edu.cess.service.content.student.*;
import cn.edu.cess.util.FileUploadUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
            userResume = new UserResume();
            userResume.setUid(userId);
            userResume.setRid(resume.getId());
            userResume.setEnabled(true);
            iUserResumeService.save(userResume);
        } else {
            //之前已经保存过简历（包括只保存附件、只保存非附件信息、都保存两者三种情况），不管附件存在与否，直接更新
            Integer rid = userResume.getRid();
            updateFilePath(filePath, rid);
        }
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
        return getById(getUserResume(userId).getRid());
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
        resume.setFileUrlVo(getFileUrlVo(userId, request));
        Integer rid = resume.getId();
        resume.setExperienceProject(iExperienceProjectService.getByResumeId(rid));
        resume.setExperienceWork(iExperienceWorkService.getByResumeId(rid));
        resume.setExperienceTrain(iExperienceTrainService.getByResumeId(rid));
        resume.setExperienceCertificateList(iExperienceCertificateService.getByResumeId(rid));
        resume.setExperienceSkillList(iExperienceSkillService.getByResumeId(rid));
        return resume;
    }

    @Override
    public void addResume(Integer userId, Resume resume) {
        Resume resumeHaved = getPreResumeByUid(userId);
        Integer rid = null;
        if (resumeHaved != null) {
            //之前已经保存过简历（包括只保存附件、只保存非附件信息、都保存两者三种情况）
            rid = resumeHaved.getId();
            //直接更新更新
            updateFilePath(resumeHaved.getFilePath(), rid);
        } else {
            //之前还未保存过简历
            save(resume);
            rid = getBySelfEvaluation(resume.getSelfEvaluation()).getId();
            UserResume userResume = new UserResume();
            userResume.setUid(userId);
            userResume.setRid(rid);
            iUserResumeService.save(userResume);
        }
        //保存简历其它内容
        iExperienceProjectService.add(rid, resume.getExperienceProject());
        iExperienceWorkService.add(rid, resume.getExperienceWork());
        iExperienceTrainService.add(rid, resume.getExperienceTrain());
//        iExperienceCertificateService.add(rid, resume.getExperienceCertificateList());
//        iExperienceSkillService.add(rid, resume.getExperienceSkillList());
    }

    private Resume getBySelfEvaluation(String selfEvaluation) {
        QueryWrapper<Resume> q = new QueryWrapper<>();
        q.eq(Constant.SELF_EVALUATION, selfEvaluation);
        return getOne(q);
    }

}
