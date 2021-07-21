package cn.edu.cess.controller.content.student;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.student.Student;
import cn.edu.cess.entity.content.student.UserStudent;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.student.*;
import cn.edu.cess.util.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/api/content/student")
public class StudentController extends AbstractClass {

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    IStudentService iStudentService;
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

    @PostMapping("/import")
    public synchronized Result importData(MultipartFile file) throws InterruptedException {
        List<Student> studentList = POIUtils.excel2Student(file, iNationService.list(), iPoliticsService.list(),
                iDepartmentService.list(), iPositionService.list(), iSpecialtyService.list());
        return ResultFactory.buildSuccessResult(iStudentService.saveBatch(studentList));
    }

    @GetMapping("/exportStatus")
    public void loading(HttpServletRequest request) {
        String key = request.getParameter("key");
        String export = redisTemplate.opsForValue().get(key);
        if (export == null || export.equals(Constant.ERROR)) {
            ResultFactory.buildFailResult("失败");
        } else {
            ResultFactory.buildSuccessResult("成功");
        }
    }

    @GetMapping("/export")
    public void exportData(HttpServletRequest request, HttpServletResponse response) {
        String key = request.getParameter("key");
        logger.info("----------------------------开始导出----------------------------");
        redisTemplate.opsForValue().set(key, Constant.ERROR,3, TimeUnit.MINUTES);
        boolean export = POIUtils.student2Excel(iStudentService.getStudents(), response);
        if (export) {
            logger.info("----------------------------导出成功----------------------------");
            redisTemplate.opsForValue().set(key, Constant.SUCCESS,1, TimeUnit.MINUTES);
        } else {
            logger.error("----------------------------导出失败----------------------------");
        }
    }


    @GetMapping("")
    public Result getStudentByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                   Student student, String[] beginDateScope) {
        return ResultFactory.buildSuccessResult(iStudentService.getStudentByPage(page, size, student, beginDateScope));
    }

    @DeleteMapping("")
    public Result deleteStudentById(@RequestParam("studentId") Integer studentId) {
        boolean b1 = iStudentService.removeById(studentId);
        boolean b2 = iUserStudentService.removeBySid(studentId);
        return ResultFactory.buildSuccessResult("");
    }

    @PutMapping("")
    public Result editStudent(@RequestBody Student student) {
        return ResultFactory.buildSuccessResult(iStudentService.updateStudent(student));
    }

    @PostMapping("")
    public Result addStudent(@RequestBody Student student) {
//        student.setId(iStudentService.getLastId() + 1);
        iStudentService.save(student);
        student = iStudentService.getByStudentId(student.getStudentId());
        UserStudent userStudent = new UserStudent();
        userStudent.setUid(student.getUserId());
        userStudent.setSid(student.getId());
        userStudent.setEnabled(false);
        iUserStudentService.save(userStudent);
        return ResultFactory.buildSuccessResult(student);
    }

    @GetMapping("/getAStudent")
    public Result getAStudent(@RequestParam Integer studentId) {
        Student student = iStudentService.getByStudentId(studentId);
        return ResultFactory.buildSuccessResult(student);
    }

    @GetMapping("/getOne")
    public Result getOneStudent(@RequestParam("userId") Integer userId) {
        //根据用户角色绑定对应的角色对象
        UserStudent userStudent = iUserStudentService.getByUid(userId);
        if (userStudent == null) {
            //未填写信息
            return ResultFactory.buildEmptyResult("");
        }
        Student student = iStudentService.listById(userStudent.getSid());
        if (userStudent.getEnabled() == false) {
            //未审核通过
            return ResultFactory.buildNotCheckResult(student);
        }
        return ResultFactory.buildSuccessResult(student);
    }

    @GetMapping("/getTotal")
    public Result getAll() {
        return ResultFactory.buildSuccessResult(iStudentService.count());
    }
}
