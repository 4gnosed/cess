package cn.edu.cess.entity.Vo;

import cn.edu.cess.entity.User;
import cn.edu.cess.entity.content.student.Student;
import lombok.Data;

/**
 * @Author Gnosed Lu
 * @Date 2020/4/11
 * @Description
 */
@Data
public class UserStudentVo {
    /**
     * 等同于userStudent的id
     */
    Integer id;
    User user;
    Student student;
    boolean enabled;
}
