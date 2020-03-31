package cn.edu.cess.controller.content.student;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.student.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/api/content/department")
public class DepartmentController extends AbstractClass {
    @Autowired
    IDepartmentService iDepartmentService;

    @GetMapping("")
    public Result listDepartment(){
        return ResultFactory.buildSuccessResult(iDepartmentService.list());
    }
}
