package cn.edu.cess.controller.content.student;


import cn.edu.cess.entity.content.student.ExperienceWork;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import cn.edu.cess.base.AbstractClass;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-20
 */
@RestController
@RequestMapping("/api/experienceWork")
public class ExperienceWorkController extends AbstractClass {
    @PostMapping("")
    public Result addResume(ExperienceWork experienceWork) {
        return ResultFactory.buildSuccessResult("");
    }
}
