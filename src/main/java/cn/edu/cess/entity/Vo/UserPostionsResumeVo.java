package cn.edu.cess.entity.Vo;

import cn.edu.cess.entity.common.User;
import cn.edu.cess.entity.content.enterprise.Positions;
import cn.edu.cess.entity.content.student.Resume;
import cn.edu.cess.entity.content.student.Student;
import lombok.Data;

/**
 * @Author Gnosed Lu
 * @Date 2020/5/5
 * @Description
 */
@Data
public class UserPostionsResumeVo {
    /**
     * 当前对象所在数组的下标
     */
    private Integer index;
    private User user;
    private Student student;
    private Resume resume;
    private Positions positions;
}
