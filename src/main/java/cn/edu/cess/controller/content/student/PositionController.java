package cn.edu.cess.controller.content.student;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.student.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/api/content/position")
public class PositionController extends AbstractClass {
    @Autowired
    IPositionService iPositionService;

    @GetMapping("")
    Result listPositions() {
        return ResultFactory.buildSuccessResult(iPositionService.list());
    }
}
