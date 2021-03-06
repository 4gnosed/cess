package cn.edu.cess.mapper.content.student;

import cn.edu.cess.entity.content.student.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-30
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
