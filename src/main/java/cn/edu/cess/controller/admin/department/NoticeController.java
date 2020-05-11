package cn.edu.cess.controller.admin.department;


import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.Vo.FileUrlVo;
import cn.edu.cess.entity.admin.department.Notice;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.admin.department.INoticeService;
import cn.edu.cess.util.FileUploadUtil;
import org.apache.ibatis.annotations.Param;
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
 * @since 2020-05-11
 */
@RestController
@RequestMapping("/api/admin/notice")
public class NoticeController extends AbstractClass {
    @Autowired
    INoticeService iNoticeService;

    @PostMapping("/img")
    public Result uploadImg(@RequestParam(value = "img") MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile == null) {
            return ResultFactory.buildFailResult("文件为空");
        }
        //保存图片
        FileUrlVo fileUrlVo = FileUploadUtil.upload(multipartFile, request, Constant.IMG_FOLDER);
        return ResultFactory.buildSuccessResult(fileUrlVo.getPath());
    }

    @PostMapping("")
    public Result publicNotice(@RequestBody Notice notice) {
        if (iNoticeService.saveNotice(notice)) {
            return ResultFactory.buildSuccessResult("");
        } else {
            return ResultFactory.buildFailResult("");
        }
    }

    @GetMapping("")
    public Result getNoticeByPage() {
        return ResultFactory.buildSuccessResult(iNoticeService.list());
    }
}
