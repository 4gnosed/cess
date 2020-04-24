package cn.edu.cess.controller;

import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.Vo.LoginUserDto;
import cn.edu.cess.entity.User;
import cn.edu.cess.entity.Vo.UserVo;
import cn.edu.cess.entity.admin.AdminRole;
import cn.edu.cess.entity.admin.AdminUserRole;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.IUserService;
import cn.edu.cess.service.admin.IAdminRoleService;
import cn.edu.cess.service.admin.IAdminUserRoleService;
import cn.edu.cess.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class LoginController extends AbstractClass {

    @Autowired
    IUserService iUserService;
    @Autowired
    IAdminUserRoleService iAdminUserRoleService;
    @Autowired
    IAdminRoleService iAdminRoleService;

    /**
     * shiro登录验证，调用subject.login(usernamePasswordToken)后，
     * Shiro 通过 Realm 里我们重写的 doGetAuthenticationInfo 方法获取到了验证信息，
     * 再根据我们在配置类里定义的 CredentialsMatcher（HashedCredentialsMatcher）
     *
     * @param requestUser
     * @param session
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody User requestUser, HttpSession session) {
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        if (StringUtil.isEmpty(username, password)) {
            String message = "账号或密码为空";
            return ResultFactory.buildFailResult(message);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        try {
            subject.login(token);
            if (!iUserService.isEnable(username)) {
                return ResultFactory.buildFailResult("用户已被禁用");
            }
            iUserService.updateLastLogin(username);
            AdminRole role = iAdminRoleService.listRoleByUsername(username).get(0);
            Integer roleId = role.getId();
            User user = iUserService.getByName(username);
            user.setPassword("");
            user.setSalt("");
            user.setRole(role.getNameZh());
            user.setRoleId(roleId);
            return ResultFactory.buildSuccessResult(user);
        } catch (AuthenticationException e) {
            String message = "账号或密码错误";
            return ResultFactory.buildFailResult(message);
        }
    }

    /**
     * 注册加密
     *
     * @param loginUserDto
     * @return
     */
    @PostMapping(value = "/register")
    public Result register(@RequestBody LoginUserDto loginUserDto) {
        String username = loginUserDto.getUsername();
        String password = loginUserDto.getPassword();
        String name = loginUserDto.getName();
        String phone = loginUserDto.getPhone();
        String email = loginUserDto.getEmail();
        int role = loginUserDto.getRole();
        if (StringUtil.isEmpty(username, password)) {
            String message = "用户名或密码为空，注册失败";
            return ResultFactory.buildFailResult(message);
        }
        if (iUserService.isExist(username)) {
            String message = "用户名已被占用";
            return ResultFactory.buildFailResult(message);
        }
        //默认生成 16 位盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String encodedPassword = new SimpleHash(Constant.MD_5, password, salt, Constant.HASH_ITERATIONS).toString();

        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setSalt(salt);
        user.setEnabled(true);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        int userId = iUserService.add(user);
        AdminUserRole adminUserRole = new AdminUserRole();
        adminUserRole.setUid(userId);
        adminUserRole.setRid(role);
        iAdminUserRoleService.save(adminUserRole);
        return ResultFactory.buildSuccessResult(loginUserDto);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping(value = "/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "成功登出";
        return ResultFactory.buildSuccessResult(message);
    }

    /**
     * 用作页面的拦截
     *
     * @return
     */
    @GetMapping("/authentication")
    public Result authentication() {
        return ResultFactory.buildSuccessResult("");
    }

    /**
     * 修改密码
     */
    @PutMapping("/password")
    public Result updatePassword(@RequestBody UserVo userVo) {
        String username = userVo.getUsername();
        String oldPassword = userVo.getOldPassword();
        String newPassword = userVo.getNewPassword();
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, oldPassword);
        try {
            subject.login(token);
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            String encodedPassword = new SimpleHash(Constant.MD_5, newPassword, salt, Constant.HASH_ITERATIONS).toString();
            iUserService.updatePassword(salt,username, encodedPassword);
            return ResultFactory.buildSuccessResult("密码修改成功");
        } catch (AuthenticationException e) {
            return ResultFactory.buildFailResult("密码错误，修改失败");
        }
    }
}
