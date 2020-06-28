package cn.edu.cess.controller.admin.department;


import cn.edu.cess.entity.admin.department.NoticePicture;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.admin.department.INoticePictureService;
import cn.edu.cess.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import cn.edu.cess.base.AbstractClass;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
    public Result getAll(HttpServletRequest request) {
        List<NoticePicture> noticePictureList = iNoticePictureService.list();
        //添加协议ip端口
        for (NoticePicture noticePicture : noticePictureList) {
            noticePicture.setPname(FileUploadUtil.getIpPort(request) + noticePicture.getPname());
        }
        return ResultFactory.buildSuccessResult(noticePictureList);
    }
}
