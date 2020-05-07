package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.enterprise.ITimesService;
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
 * @since 2020-05-03
 */
@RestController
@RequestMapping("/api/time")
public class TimesController extends AbstractClass {

    @Autowired
    ITimesService iTimesService;

    @GetMapping("")
    public Result list() {
        return ResultFactory.buildSuccessResult(iTimesService.list());
    }
}
