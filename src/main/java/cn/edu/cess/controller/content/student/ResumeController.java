package cn.edu.cess.controller.content.student;


import cn.edu.cess.entity.Vo.FileUrlVo;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.student.IResumeService;
import cn.edu.cess.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.edu.cess.base.AbstractClass;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-20
 */
@RestController
@RequestMapping("/api/resume")
public class ResumeController extends AbstractClass {

    @Autowired
    IResumeService iResumeService;

    /**
     * 简历附件
     */
    @PostMapping("/file")
    public synchronized Result saveResumeFile(@RequestParam(value = "file") MultipartFile multipartFile, @RequestParam(value = "userId") Integer userId, HttpServletRequest request) {
        if (userId == null) {
            return ResultFactory.buildFailResult("无用户操作");
        }
        if (multipartFile == null) {
            return ResultFactory.buildFailResult("接收文件为空");
        }
        //保存文件
        FileUrlVo fileUrlVo = FileUploadUtil.upload(multipartFile, request);
        //保存文件路径
        iResumeService.saveFilePath(fileUrlVo.getFilePath(), userId);
        return ResultFactory.buildSuccessResult(fileUrlVo.getPath());
    }

    /**
     * 根据用户id查找简历附件
     */
    @GetMapping("/file")
    public Result getResumeFile(@RequestParam(value = "userId") Integer userId, HttpServletRequest request) {
        return ResultFactory.buildSuccessResult(iResumeService.getFileUrlVo(userId, request));
    }

}
