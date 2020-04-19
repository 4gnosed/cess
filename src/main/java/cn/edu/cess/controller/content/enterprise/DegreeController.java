package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.enterprise.IDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import cn.edu.cess.base.AbstractClass;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/api/degree")
public class DegreeController extends AbstractClass {
    @Autowired
    IDegreeService iDegreeService;

    @GetMapping("")
    public Result getAll() {
        return ResultFactory.buildSuccessResult(iDegreeService.list());
    }
}
