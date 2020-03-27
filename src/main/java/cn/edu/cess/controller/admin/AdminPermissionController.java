package cn.edu.cess.controller.admin;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.entity.User;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/api/admin")
public class AdminPermissionController extends AbstractClass {
}
