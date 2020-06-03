package cn.edu.cess.controller.admin.department;


import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.admin.department.Notice;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.admin.department.INoticeEnterpriseService;
import cn.edu.cess.service.admin.department.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.edu.cess.base.AbstractClass;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-11
 */
@RestController
@RequestMapping("/api/noticeEnterprise")
public class NoticeEnterpriseController extends AbstractClass {
    @Autowired
    INoticeEnterpriseService iNoticeEnterpriseService;
    @Autowired
    INoticeService iNoticeService;

    @GetMapping("")
    public Result getNotice(@RequestParam Integer enterpriseId) {
        Notice notice = iNoticeEnterpriseService.getByEId(enterpriseId);
        if (notice == null) {
            return ResultFactory.buildFailResult("没有通知");
        }
        return ResultFactory.buildSuccessResult(notice);
    }

    @GetMapping("/byPage")
    public Result getNoticeByPage(@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer size) {
        return ResultFactory.buildSuccessResult(iNoticeService.listByPage(page, size, Constant.Enterprise_NOTICE_TYPE));
    }

    @PostMapping("")
    public Result saveNotice(@RequestBody Notice notice, @RequestParam Integer enterpriseId) {
        if (iNoticeEnterpriseService.saveNotice(notice, enterpriseId)) {
            return ResultFactory.buildSuccessResult("");
        }
        return ResultFactory.buildFailResult("");
    }
}
