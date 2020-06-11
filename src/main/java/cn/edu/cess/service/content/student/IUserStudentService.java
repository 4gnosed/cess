package cn.edu.cess.service.content.student;

import cn.edu.cess.entity.content.student.UserStudent;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-09
 */
public interface IUserStudentService extends IService<UserStudent> {

    UserStudent getByUid(int id);

    List<UserStudent> getUserStudentsByPage(Integer page, Integer size);

    void updateStatus(boolean enabled, String id);

    boolean removeByUid(int userId);

    boolean removeBySid(Integer studentId);
}
