package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.student.*;
import cn.edu.cess.mapper.content.student.StudentMapper;
import cn.edu.cess.result.ResultPage;
import cn.edu.cess.service.content.student.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-30
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    INationService iNationService;
    @Autowired
    IPoliticsService iPoliticsService;
    @Autowired
    IDepartmentService iDepartmentService;
    @Autowired
    ISpecialtyService iSpecialtyService;
    @Autowired
    IPositionService iPositionService;

    /**
     * mybatis-plus分页插件
     *
     * @param page
     * @param size
     * @param student
     * @param beginDateScope
     * @return
     */
    @Override
    public ResultPage getStudentByPage(Integer page, Integer size, Student student, String[] beginDateScope) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        String name = student.getName();
        Integer studentId = student.getStudentId();
        Integer departmentId = student.getDepartmentId();
        Integer specialtyId = student.getSpecialtyId();
        Integer positionId = student.getPositionId();
        Integer politicId = student.getPoliticId();
        Integer nationId = student.getNationId();
//        student.getBeginDate()
        //查询条件
        if (name != null) {
            queryWrapper.like(Constant.NAME, name);
        }
        if (studentId != null) {
            queryWrapper.like(Constant.STUDENT_ID, studentId);
        }
        if (departmentId != null) {
            queryWrapper.eq(Constant.DEPARTMENT_ID, departmentId);
        }
        if (specialtyId != null) {
            queryWrapper.eq(Constant.SPECIALTY_ID, specialtyId);
        }
        if (positionId != null) {
            queryWrapper.eq(Constant.POSITION_ID, positionId);
        }
        if (politicId != null) {
            queryWrapper.eq(Constant.POLITIC_ID, politicId);
        }
        if (nationId != null) {
            queryWrapper.eq(Constant.NATION_ID, nationId);
        }
        if (beginDateScope != null) {
            queryWrapper.between(Constant.BEGIN_DATE, beginDateScope[0], beginDateScope[1]);
        }

        Page<Student> studentPage = page(new Page<>(page, size), queryWrapper);
        long total = studentPage.getTotal();
        List<Student> studentList = studentPage.getRecords();

        QueryWrapper<Nation> nationQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Politics> politicsQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Department> departmentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Specialty> specialtyQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Position> positionQueryWrapper = new QueryWrapper<>();
        //填充民族、政治面貌等属性
        for (Student stu : studentList) {
            nationQueryWrapper.clear();
            politicsQueryWrapper.clear();
            departmentQueryWrapper.clear();
            specialtyQueryWrapper.clear();
            positionQueryWrapper.clear();
            stu.setNation(iNationService.getOne(nationQueryWrapper.eq(Constant.ID, stu.getNationId())));
            stu.setPolitics(iPoliticsService.getOne(politicsQueryWrapper.eq(Constant.ID, stu.getPoliticId())));
            stu.setDepartment(iDepartmentService.getOne(departmentQueryWrapper.eq(Constant.ID, stu.getDepartmentId())));
            stu.setSpecialty(iSpecialtyService.getOne(specialtyQueryWrapper.eq(Constant.ID, stu.getSpecialtyId())));
            stu.setPosition(iPositionService.getOne(positionQueryWrapper.eq(Constant.ID, stu.getPositionId())));
        }
        ResultPage resultPage = new ResultPage();
        resultPage.setData(studentList);
        resultPage.setTotal(total);
        return resultPage;
    }
}
