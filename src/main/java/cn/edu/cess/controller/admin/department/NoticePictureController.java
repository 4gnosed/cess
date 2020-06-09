package cn.edu.cess.controller.admin.department;


import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.admin.department.INoticePictureService;
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
 * @since 2020-06-03
 */
@RestController
@RequestMapping("/api/noticePicture")
public class NoticePictureController extends AbstractClass {
    @Autowired
    INoticePictureService iNoticePictureService;

    @GetMapping("")
    public Result getAll(){
        return ResultFactory.buildSuccessResult(iNoticePictureService.list());
    }
}
