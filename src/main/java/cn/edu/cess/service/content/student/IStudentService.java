package cn.edu.cess.service.content.student;

import cn.edu.cess.entity.content.student.Student;
import cn.edu.cess.result.ResultPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-30
 */
public interface IStudentService extends IService<Student> {

    ResultPage getStudentByPage(Integer page, Integer size, Student student, String[] beginDateScope);

    boolean updateStudent(Student student);

    List<Student> getStudents();

    Student listById(Integer sid);

    Integer getLastId();
}
