package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.entity.content.enterprise.EnterprisePlace;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.enterprise.IEnterprisePlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/api/enterprisePlace")
public class EnterprisePlaceController extends AbstractClass {
    @Autowired
    IEnterprisePlaceService iEnterprisePlaceService;

    @PostMapping("")
    public Result addEnterprisePlace(@RequestParam Integer eid, @RequestParam Integer pid,
                                     @RequestParam Integer talkTimeId) {
        boolean saved = iEnterprisePlaceService.save(eid, pid, talkTimeId);
        if (saved) {
            return ResultFactory.buildSuccessResult("");
        } else {
            return ResultFactory.buildFailResult("安排失败");
        }
    }
}
