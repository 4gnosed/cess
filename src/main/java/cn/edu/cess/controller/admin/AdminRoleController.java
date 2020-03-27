package cn.edu.cess.controller.admin;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.IAdminMenuService;
import cn.edu.cess.service.IAdminPermissionService;
import cn.edu.cess.service.IAdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
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
    public Result listRoles(){
        return ResultFactory.buildSuccessResult(iAdminRoleService.list());
    }

    @GetMapping("/perm")
    public Result listPermissions(){
        return ResultFactory.buildSuccessResult(iAdminPermissionService.list());
    }

    @GetMapping("/menu")
    public Result listMenus(){
        return ResultFactory.buildSuccessResult(iAdminMenuService.list());
    }
}
