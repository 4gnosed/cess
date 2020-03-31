package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.student.Specialty;
import cn.edu.cess.entity.content.student.Student;
import cn.edu.cess.service.content.student.IDepartmentService;
import cn.edu.cess.service.content.student.ISpecialtyService;
import cn.edu.cess.service.content.student.IStudentService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Gnosed Lu
 * @Date 2020/3/31
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceImplTest extends AbstractClass {

    @Autowired
    IStudentService iStudentService;
    @Autowired
    IDepartmentService iDepartmentService;
    @Autowired
    ISpecialtyService iSpecialtyService;

    /**
     * 生成学号
     */
    @Test
    public void generateStudentId() {
        List<Student> studentList = iStudentService.list();
        Integer studentId = studentList.get(0).getStudentId();
        for (Student student : studentList) {
            student.setStudentId(studentId++);
        }
        iStudentService.updateBatchById(studentList);
    }

    /**
     * 随机分配学生所属院系
     */
    @Test
    public void distributeDepartment() {
        List<Student> studentList = iStudentService.list();
        Random random = new Random();
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        for (Student student : studentList) {
            updateWrapper.clear();
            updateWrapper.eq(Constant.ID, student.getId());
            int rand = random.nextInt(16) + 1;
            student.setDepartmentId(rand);
            iStudentService.update(student, updateWrapper);
            logger.info("随机id:"+rand);
        }
    }

    /**
     * 随机分配专业，对应起学生所属院系的关系
     */
    @Test
    public void distributeSpecialty() {
        List<Student> studentList = iStudentService.list();
        List<Specialty> specialtyList = iSpecialtyService.list();
        //1.通过院系id获取该院系设有的专业
        //2.随机分配一个专业给学生
        Integer departmentId;
        ArrayList<Integer> specialtiesFromADepartment;
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        for (Student student : studentList) {
            specialtiesFromADepartment = new ArrayList<>();
            departmentId = student.getDepartmentId();
            //1.
            for (Specialty specialty : specialtyList) {
                if (specialty.getDepartmentId() == departmentId) {
                    specialtiesFromADepartment.add(specialty.getId());
                }
            }
            //2.
            int size = specialtiesFromADepartment.size();
            Random random = new Random();
            student.setSpecialtyId(specialtiesFromADepartment.get(random.nextInt(size)));

            updateWrapper.clear();
            updateWrapper.eq(Constant.ID, student.getId());
            iStudentService.update(student, updateWrapper);

            logger.info("学生" + student.getId() + "专业id：" + student.getSpecialtyId().toString());
        }
    }

    @Test
    public void randTest() {
        for (int i = 0; i < 100; i++) {
//            int rand = (int) (Math.random() * 14);
            Random random = new Random();
            int rand = random.nextInt(10);
            logger.info("" + rand);
        }
    }
}