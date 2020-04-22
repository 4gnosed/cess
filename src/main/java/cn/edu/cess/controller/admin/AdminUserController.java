package cn.edu.cess.controller.admin;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.entity.Vo.AdminUserDto;
import cn.edu.cess.entity.User;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-24
 */
@RestController
@RequestMapping("/api/admin/user")
public class AdminUserController extends AbstractClass {
    @Autowired
    IUserService iUserService;

    @GetMapping("")
    public Result listAllUsers() {
        return ResultFactory.buildSuccessResult(iUserService.listAll());
    }

    @PutMapping("")
    public Result editUser(@RequestBody AdminUserDto adminUserDto) {
        return ResultFactory.buildSuccessResult(iUserService.editUser(adminUserDto));
    }

    @DeleteMapping("/delete")
    public Result deleteById(@RequestParam("id") int userId) {
        return ResultFactory.buildSuccessResult(iUserService.removeUser(userId));
    }

    @DeleteMapping("/deletes")
    public Result deleteByUsers(@RequestBody String users) {
        List<User> userList = JSONObject.parseArray(users, User.class);
        return ResultFactory.buildSuccessResult(iUserService.removeUsers(userList));
    }

    @PutMapping("/password")
    public Result resetPassword(@RequestParam("username") String username) {
        return ResultFactory.buildSuccessResult(iUserService.resetPassword(username));
    }

    @PutMapping("/status")
    public Result changeUserStatus(@RequestBody User user) {
        return ResultFactory.buildSuccessResult(iUserService.updateUserStatus(user));
    }
}
