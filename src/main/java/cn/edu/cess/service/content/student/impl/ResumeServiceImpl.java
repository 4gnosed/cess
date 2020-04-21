package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.Vo.FileUrlVo;
import cn.edu.cess.entity.content.student.Resume;
import cn.edu.cess.entity.content.student.UserResume;
import cn.edu.cess.mapper.content.student.ResumeMapper;
import cn.edu.cess.service.content.student.IResumeService;
import cn.edu.cess.service.content.student.IUserResumeService;
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

    @Override
    public void saveFilePath(String filePath, Integer userId) {
        UserResume userResume = getUserResume(userId);
        if (userResume == null) {
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
            Integer rid = userResume.getRid();
            UpdateWrapper<Resume> rUpdateWrapper = new UpdateWrapper<>();
            rUpdateWrapper.eq(Constant.ID, rid).set(Constant.FILE_PATH, filePath);
            update(rUpdateWrapper);
        }
    }

    public UserResume getUserResume(Integer userId) {
        QueryWrapper<UserResume> urQueryWrapper = new QueryWrapper<>();
        urQueryWrapper.eq(Constant.UID, userId);
        return iUserResumeService.getOne(urQueryWrapper);
    }

    @Override
    public FileUrlVo getFileUrlVo(Integer userId, HttpServletRequest request) {
        UserResume userResume = getUserResume(userId);
        Resume resume = getById(userResume.getRid());
        FileUrlVo fileUrlVo = new FileUrlVo();
        fileUrlVo.setFilePath(resume.getFilePath());
        fileUrlVo.setIpPort(FileUploadUtil.getIpPort(request));
        fileUrlVo.setPath(fileUrlVo.getPath());
        fileUrlVo.setFileName(fileUrlVo.getFileName());
        return fileUrlVo;
    }

    public Resume getByFilePath(String filePath) {
        QueryWrapper<Resume> q = new QueryWrapper<>();
        q.eq(Constant.FILE_PATH, filePath);
        return getOne(q);
    }
}
