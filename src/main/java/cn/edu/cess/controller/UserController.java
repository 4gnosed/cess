package cn.edu.cess.controller;

import cn.edu.cess.entity.Vo.FileUrlVo;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.common.IUserService;
import cn.edu.cess.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Gnosed Lu
 * @Date 2020/6/9
 * @Description
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    IUserService iUserService;

    @PostMapping("/avatar")
    public Result saveAvatar(@RequestParam(value = "avatar") MultipartFile multipartFile,
                             @RequestParam(value = "userId") Integer userId, HttpServletRequest request) {
        if (multipartFile == null) {
            return ResultFactory.buildFailResult("头像文件为空");
        }
        //保存图片
        FileUrlVo fileUrlVo = FileUploadUtil.uploadAvatar(multipartFile, request);
        if(fileUrlVo==null){
            return ResultFactory.buildFailResult("保存失败");
        }
        //保存图片路径
        if (iUserService.saveAvatarPath(fileUrlVo.getFilePath(), userId)) {
            //返回用户对象
            return ResultFactory.buildSuccessResult(iUserService.fillUser(request, null, userId));
        } else {
            return ResultFactory.buildFailResult("保存失败");
        }
    }

    @GetMapping("/queryEnableUser")
    public Result queryEnableUser(){
        return ResultFactory.buildSuccessResult(iUserService.queryEnableUser());
    }
}
