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
import cn.edu.cess.util.FileUploadUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody() User requestUser, HttpServletRequest request) {
        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(requestUser.isRememberMe());
        try {
            subject.login(token);
            if (!iUserService.isEnable(username)) {
                return ResultFactory.buildFailResult("用户已被禁用");
            }
            iUserService.updateLastLogin(username);
            User user = iUserService.fillUser(request, username, null);
            return ResultFactory.buildSuccessResult(user);
        } catch (UnknownAccountException uae) {
            //username wasn't in the system, show them an error message?
            return ResultFactory.buildFailResult("账号或密码错误");
        } catch (IncorrectCredentialsException ice) {
            //password didn't match, try again?
            return ResultFactory.buildFailResult("凭证错误");
        } catch (ExpiredCredentialsException ece) {
            return ResultFactory.buildFailResult("凭证已过期");
        } catch (LockedAccountException lae) {
            //account for that username is locked - can't login.  Show them a message?
            return ResultFactory.buildFailResult("账号被锁定");
        } catch (DisabledAccountException dae) {
            return ResultFactory.buildFailResult("账号被禁用");
        } catch (ConcurrentAccessException cae) {
            return ResultFactory.buildFailResult("并发访问异常");
        } catch (ExcessiveAttemptsException eae) {
            return ResultFactory.buildFailResult("认证次数超过限制");
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
        user.setAvatarPath(Constant.AVATAR1_PATH + Constant.DEFAULT_HEAD_PNG);
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
     * 用作页面的拦截，验证用户登录状态
     *
     * @return
     */
    @GetMapping("/authentication")
    public Result authentication() {
        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()) {
            return ResultFactory.buildSuccessResult("");
        } else {
            return ResultFactory.buildFailResult("请重新登录");
        }
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
            iUserService.updatePassword(salt, username, encodedPassword);
            return ResultFactory.buildSuccessResult("密码修改成功");
        } catch (AuthenticationException e) {
            return ResultFactory.buildFailResult("密码错误，修改失败");
        }
    }
}
