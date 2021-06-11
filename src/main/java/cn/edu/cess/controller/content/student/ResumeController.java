package cn.edu.cess.controller.content.student;


import cn.edu.cess.entity.Vo.FileUrlVo;
import cn.edu.cess.entity.content.student.Resume;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.common.IMessageService;
import cn.edu.cess.service.content.enterprise.ISheetContractService;
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

    @Autowired
    IMessageService iMessageService;

    @Autowired
    ISheetContractService iSheetContractService;

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
        FileUrlVo fileUrlVo = FileUploadUtil.uploadResume(multipartFile, request);
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
        return ResultFactory.buildSuccessResult("");
    }

    @PostMapping("/send")
    public Result sendResume(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "positionId") Integer positionId,
                             @RequestParam(value = "rid") Integer rid) {
        //保存简历-职位关系
        boolean saved = iResumePositionsService.saveResumePositions(positionId, rid);
        //发送投递消息，消息-职位的多对多关系
        iMessageService.sendMessage(userId, positionId);
        //已经投递过
        if (!saved) {
            return ResultFactory.buildFailResult("您已经投递过");
        } else {
            return ResultFactory.buildSuccessResult("");
        }
    }

    @PutMapping("")
    public Result updateResume(@RequestParam(value = "userId") Integer userId, @RequestBody Resume resume) {
        if (iResumeService.updateResume(userId, resume)) {
            return ResultFactory.buildSuccessResult("");
        } else {
            return ResultFactory.buildFailResult("更新失败");
        }
    }

    @GetMapping("/getUserPositionsResumeVos")
    public Result getUserPositionsResumeVos(@RequestParam(value = "userId") Integer userId, HttpServletRequest request) {
        return ResultFactory.buildSuccessResult(iResumeService.getUserPositionsResumeVos(userId, request));
    }

    @GetMapping("/getResumePositionsList")
    public Result getResumePositionsVos(@RequestParam(value = "rid") Integer rid) {
        return ResultFactory.buildSuccessResult(iResumeService.getResumePositionsVos(rid));
    }

    @GetMapping("/getContractVos")
    public Result getContractVosVosByPage(@RequestParam(defaultValue = "1") Integer page,
                                                         @RequestParam(defaultValue = "20") Integer size) {
        return ResultFactory.buildSuccessResult(
                iResumePositionsService.getContractVosByPage(page, size));
    }

    @PutMapping("/state")
    public Result changeResumeState(@RequestParam Integer rid, @RequestParam Integer pid, @RequestParam Integer stateId) {
//        if (stateId == Constant.EMPLOYED_STATE_ID) {
//            Integer contractId = iResumePositionsService.getContractIdByRidPid(rid, pid);
//            iSheetContractService.enabled(contractId);
//        }
        if (iResumePositionsService.changeState(rid, pid, stateId)) {
            return ResultFactory.buildSuccessResult("");
        }
        return ResultFactory.buildFailResult("未填写当前状态信息");
    }

    @DeleteMapping("/state/delete")
    public Result changeResumePositions(@RequestParam Integer rid, @RequestParam Integer pid) {
        if (iResumePositionsService.deleteResumePositions(rid, pid)) {
            return ResultFactory.buildSuccessResult("");
        }
        return ResultFactory.buildFailResult("");
    }

}
