package cn.edu.cess.controller.admin;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.entity.AdminMenu;
import cn.edu.cess.entity.AdminRole;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.IAdminMenuService;
import cn.edu.cess.service.IAdminPermissionService;
import cn.edu.cess.service.IAdminRoleService;
import cn.edu.cess.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/api/admin/role")
public class AdminRoleController extends AbstractClass {
    @Autowired
    IAdminRoleService iAdminRoleService;
    @Autowired
    IAdminPermissionService iAdminPermissionService;
    @Autowired
    IAdminMenuService iAdminMenuService;

    @GetMapping("")
    public Result listRoles() {
        return ResultFactory.buildSuccessResult(iAdminRoleService.listRoles());
    }

    @PostMapping("")
    public Result addRole(@RequestBody AdminRole role) {
        if (iAdminRoleService.saveRole(role)) {
            return ResultFactory.buildSuccessResult(role);
        }
        return ResultFactory.buildFailResult("角色名已存在");
    }

    @PutMapping("")
    public Result editRole(@RequestBody AdminRole role) {
        return ResultFactory.buildSuccessResult(iAdminRoleService.editRole(role));
    }

    @GetMapping("/perm")
    public Result listPermissions() {
        return ResultFactory.buildSuccessResult(iAdminPermissionService.list());
    }

    @GetMapping("/menu")
    public Result listMenus() {
        List<AdminMenu> menus = iAdminMenuService.list();
        TreeUtil.treeMenus(menus);
        return ResultFactory.buildSuccessResult(menus);
    }

    @PutMapping("/status")
    public Result changeRoleStatus(@RequestBody AdminRole role) {
        if (iAdminRoleService.changeRoleStatus(role)) {
            return ResultFactory.buildSuccessResult("");
        }
        return ResultFactory.buildFailResult("");
    }
}
