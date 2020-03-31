package cn.edu.cess.controller.content.student;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.entity.content.student.Student;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.student.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    IStudentService iStudentService;

    @GetMapping("")
    public Result getStudentByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                   Student student, String[] beginDateScope) {
        return ResultFactory.buildSuccessResult(iStudentService.getStudentByPage(page, size, student, beginDateScope));
    }

    @DeleteMapping("")
    public Result deleteStudentById(@RequestParam("studentId") Integer studentId) {
        return ResultFactory.buildSuccessResult(iStudentService.removeById(studentId));
    }

    @PutMapping("")
    public Result editStudent(@RequestBody Student student){
        return ResultFactory.buildSuccessResult(iStudentService.updateStudent(student));
    }

    @PostMapping("")
    public Result addStudent(@RequestBody Student student){
        return ResultFactory.buildSuccessResult(iStudentService.save(student));
    }
}
