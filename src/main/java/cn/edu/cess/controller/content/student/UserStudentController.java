package cn.edu.cess.controller.content.student;


import cn.edu.cess.entity.common.User;
import cn.edu.cess.entity.Vo.UserStudentVo;
import cn.edu.cess.entity.content.student.Student;
import cn.edu.cess.entity.content.student.UserStudent;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.result.ResultPage;
import cn.edu.cess.service.common.IUserService;
import cn.edu.cess.service.content.student.IStudentService;
import cn.edu.cess.service.content.student.IUserStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.cess.base.AbstractClass;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-09
 */
@RestController
@RequestMapping("/api/userStudent")
public class UserStudentController extends AbstractClass {
    @Autowired
    IUserService iUserService;
    @Autowired
    IUserStudentService iUserStudentService;
    @Autowired
    IStudentService iStudentService;

    @RequestMapping("")
    public Result getUserStudentVoByPage(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size) {
        ArrayList<UserStudentVo> userStudentVos = new ArrayList<>();
        List<UserStudent> userStudentList = iUserStudentService.getUserStudentsByPage(page, size);
        //遍历所有用户-学生的关系对象
        for (UserStudent userStudent : userStudentList) {
            UserStudentVo userStudentVo = new UserStudentVo();
            User user = iUserService.getById(userStudent.getUid());
            Student student = iStudentService.getById(userStudent.getSid());
            //填充学生其它信息
            iStudentService.fillData(student);
            userStudentVo.setId(userStudent.getId());
            userStudentVo.setUser(user);
            userStudentVo.setStudent(student);
            userStudentVo.setEnabled(userStudent.getEnabled());
            userStudentVos.add(userStudentVo);
        }
        ResultPage resultPage = new ResultPage();
        resultPage.setTotal((long) userStudentList.size());
        resultPage.setData(userStudentVos);
        return ResultFactory.buildSuccessResult(resultPage);
    }

    @PutMapping("/status")
    public Result checkStatus(@RequestParam("enabled") boolean enabled, @RequestParam("id") String id) {
        iUserStudentService.updateStatus(enabled, id);
        return ResultFactory.buildSuccessResult("");
    }
}
