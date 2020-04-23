package cn.edu.cess.controller.content.student;


import cn.edu.cess.entity.Vo.FileUrlVo;
import cn.edu.cess.entity.content.student.Resume;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.student.IResumePositionsService;
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

    @Autowired
    IResumePositionsService iResumePositionsService;

    /**
     * 简历附件
     */
    @PostMapping("/file")
    public Result saveResumeFile(@RequestParam(value = "file") MultipartFile multipartFile, @RequestParam(value = "userId") Integer userId, HttpServletRequest request) {
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

    @GetMapping("")
    public Result getResume(@RequestParam(value = "userId") Integer userId, HttpServletRequest request) {
        return ResultFactory.buildSuccessResult(iResumeService.getCompleteResumeByUid(userId, request));
    }

    /**
     * 个人中心模块的添加简历接口
     *
     * @param userId
     * @param resume
     * @return
     */
    @PostMapping("/personCenter")
    public Result addResumeInPersonCenter(@RequestParam(value = "userId") Integer userId, @RequestBody Resume resume) {
        iResumeService.addResume(userId, resume);
        return ResultFactory.buildSuccessResult("");
    }

    /**
     * 求职模块的添加简历接口
     *
     * @param userId
     * @param positionId
     * @param resume
     * @return
     */
    @PostMapping("")
    public Result addResumeInPositions(@RequestParam(value = "userId") Integer userId,
                                       @RequestParam(value = "positionId") Integer positionId, @RequestBody Resume resume) {
        Integer rid = iResumeService.addResume(userId, resume);
        iResumePositionsService.save(positionId, rid);
        return ResultFactory.buildSuccessResult("");
    }

    @PutMapping("")
    public Result updateResume(@RequestParam(value = "userId") Integer userId, @RequestBody Resume resume) {
        if (iResumeService.updateResume(userId, resume)) {
            return ResultFactory.buildSuccessResult("");
        } else {
            return ResultFactory.buildFailResult("更新失败");
        }
    }

}
