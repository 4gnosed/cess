package cn.edu.cess.controller.admin;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.entity.admin.AdminUserRole;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.admin.IAdminUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-23
 */
@RestController
@RequestMapping("/api/userRole")
public class AdminUserRoleController extends AbstractClass {
    @Autowired
    IAdminUserRoleService iAdminUserRoleService;

    @GetMapping("")
    public Result get(@RequestParam Integer userId) {
        List<AdminUserRole> userRoles = iAdminUserRoleService.getUserRoleByUid(userId);
        return ResultFactory.buildSuccessResult(userRoles.get(0).getRid());
    }
}
