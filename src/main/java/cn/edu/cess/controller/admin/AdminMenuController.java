package cn.edu.cess.controller.admin;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.IAdminMenuService;
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
@RequestMapping("/api")
public class AdminMenuController extends AbstractClass {
    @Autowired
    IAdminMenuService iAdminMenuService;

    @GetMapping("/menu")
    public Result menu(){
        return ResultFactory.buildSuccessResult(iAdminMenuService.getMenusByCurrentUser());
    }
}
