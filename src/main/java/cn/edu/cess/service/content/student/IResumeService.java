package cn.edu.cess.service.content.student;

import cn.edu.cess.entity.Vo.FileUrlVo;
import cn.edu.cess.entity.content.student.Resume;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-20
 */
public interface IResumeService extends IService<Resume> {

    void saveFilePath(String filePath, Integer userId);

    FileUrlVo getFileUrlVo(Integer userId, HttpServletRequest request);
}
