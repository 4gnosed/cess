package cn.edu.cess.controller.content.student;


import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.student.IResumePositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import cn.edu.cess.base.AbstractClass;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-22
 */
@RestController
@RequestMapping("/api/resumePositions")
public class ResumePositionsController extends AbstractClass {
    @Autowired
    IResumePositionsService iResumePositionsService;

    @GetMapping("/getAll")
    public Result getAll(){
        return ResultFactory.buildSuccessResult(iResumePositionsService.list());
    }
}
