package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.student.*;
import cn.edu.cess.mapper.content.student.StudentMapper;
import cn.edu.cess.result.ResultPage;
import cn.edu.cess.service.content.student.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    IUserStudentService iUserStudentService;
    @Autowired
    StudentMapper studentMapper;

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
        fillData(studentList);

        ResultPage resultPage = new ResultPage();
        resultPage.setData(studentList);
        resultPage.setTotal(total);
        return resultPage;
    }

    /**
     * 填充多个学生民族、政治面貌等属性
     *
     * @param studentList
     */
    @Override
    public void fillData(List<Student> studentList) {
        List<Integer> nationIds = studentList.stream().map(o -> o.getNationId()).collect(Collectors.toList());
        List<Integer> politicIds = studentList.stream().map(o -> o.getPoliticId()).collect(Collectors.toList());
        List<Integer> departmentIds = studentList.stream().map(o -> o.getDepartmentId()).collect(Collectors.toList());
        List<Integer> specialtyIds = studentList.stream().map(o -> o.getSpecialtyId()).collect(Collectors.toList());
        List<Integer> positionIds = studentList.stream().map(o -> o.getPositionId()).collect(Collectors.toList());
        List<Nation> nations = iNationService.listByIds(nationIds);
        List<Politics> politics = iPoliticsService.listByIds(politicIds);
        List<Department> departments = iDepartmentService.listByIds(departmentIds);
        List<Specialty> specialties = iSpecialtyService.listByIds(specialtyIds);
        List<Position> positions = iPositionService.listByIds(positionIds);
        Map<Integer, List<Nation>> nation = nations.stream().collect(Collectors.groupingBy(o -> o.getId()));
        Map<Integer, List<Politics>> plitic = politics.stream().collect(Collectors.groupingBy(o -> o.getId()));
        Map<Integer, List<Department>> department = departments.stream().collect(Collectors.groupingBy(o -> o.getId()));
        Map<Integer, List<Specialty>> special = specialties.stream().collect(Collectors.groupingBy(o -> o.getId()));
        Map<Integer, List<Position>> position = positions.stream().collect(Collectors.groupingBy(o -> o.getId()));
        // 填充一个学生民族、政治面貌等属性
        for (Student stu : studentList) {
            List<Nation> ns = nation.get(stu.getNationId());
            List<Politics> ps = plitic.get(stu.getPoliticId());
            List<Department> ds = department.get(stu.getDepartmentId());
            List<Specialty> ss = special.get(stu.getSpecialtyId());
            List<Position> pos = position.get(stu.getPositionId());
            stu.setPolitics(ps.size()>0?ps.get(0):null);
            stu.setNation(ns.size()>0?ns.get(0):null);
            stu.setDepartment(ds.size()>0?ds.get(0):null);
            stu.setSpecialty(ss.size()>0?ss.get(0):null);
            stu.setPosition(pos.size()>0?pos.get(0):null);
        }
    }

    /**
     * 填充一个学生民族、政治面貌等属性
     *
     * @param stu
     */
    @Override
    public void fillData(Student stu) {
        QueryWrapper<Nation> nationQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Politics> politicsQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Department> departmentQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Specialty> specialtyQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Position> positionQueryWrapper = new QueryWrapper<>();
        stu.setNation(iNationService.getOne(nationQueryWrapper.eq(Constant.ID, stu.getNationId())));
        stu.setPolitics(iPoliticsService.getOne(politicsQueryWrapper.eq(Constant.ID, stu.getPoliticId())));
        stu.setDepartment(iDepartmentService.getOne(departmentQueryWrapper.eq(Constant.ID, stu.getDepartmentId())));
        stu.setSpecialty(iSpecialtyService.getOne(specialtyQueryWrapper.eq(Constant.ID, stu.getSpecialtyId())));
        stu.setPosition(iPositionService.getOne(positionQueryWrapper.eq(Constant.ID, stu.getPositionId())));
    }

    @Override
    public Student getByStudentId(Integer studentId) {
//        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(Constant.STUDENT_ID, studentId);
//        return getOne(queryWrapper);
        //使用mapper
        return studentMapper.selectByPrimaryKey(studentId);
    }

    @Override
    public Student getByUid(Integer uid) {
        UserStudent userStudent = iUserStudentService.getByUid(uid);
        return getById(userStudent.getSid());
    }

    @Override
    public boolean updateStudent(Student student) {
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(Constant.ID, student.getId());
        return update(student, updateWrapper);
    }

    @Override
    public List<Student> getStudents() {
        List<Student> studentList = list();
        fillData(studentList);
        return studentList;
    }

    @Override
    public Student listById(Integer sid) {
        QueryWrapper<Student> q = new QueryWrapper<>();
        q.eq(Constant.ID, sid);
        Student student = getOne(q);
        fillData(student);
        return student;
    }

    /**
     * 获取表中最后一条记录id，Max(id)
     *
     * @return
     */
    @Override
    public Integer getLastId() {
        QueryWrapper<Student> q = new QueryWrapper<>();
        q.orderByDesc(Constant.ID).last("limit 0 , 1");
        return list(q).get(0).getId();
    }
}
