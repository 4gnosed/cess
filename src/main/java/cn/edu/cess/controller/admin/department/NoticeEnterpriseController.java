package cn.edu.cess.controller.admin.department;


import cn.edu.cess.entity.admin.department.Notice;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.admin.department.INoticeEnterpriseService;
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

    @GetMapping("")
    public Result getNotice(@RequestParam Integer enterpriseId) {
        Notice notice = iNoticeEnterpriseService.getByEId(enterpriseId);
        if (notice == null) {
            return ResultFactory.buildFailResult("没有通知");
        }
        return ResultFactory.buildSuccessResult(notice);
    }

    @PostMapping("")
    public Result saveNotice(@RequestBody Notice notice, @RequestParam Integer enterpriseId) {
        if (iNoticeEnterpriseService.saveNotice(notice, enterpriseId)) {
            return ResultFactory.buildSuccessResult("");
        }
        return ResultFactory.buildFailResult("");
    }
    
}
