package cn.edu.cess.controller;

import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.User;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.IUserService;
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
    private IUserService iUserService;

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
//        User user = iUserService.list(username, password);
//        if (user != null) {
//            //保持登录状态
//            session.setAttribute("user", user);
//            return ResultFactory.buildSuccessResult(user);
//        } else {
//            String message = "账号或密码错误";
//            return ResultFactory.buildFailResult(message);
//        }
        if (StringUtil.isEmpty(username, password)) {
            String message = "账号或密码为空";
            return ResultFactory.buildFailResult(message);
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        try {
            subject.login(token);
            iUserService.updateLastLogin(username);
            return ResultFactory.buildSuccessResult(token);
        } catch (AuthenticationException e) {
            String message = "账号或密码错误";
            return ResultFactory.buildFailResult(message);
        }
    }

    /**
     * 注册加密
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/register")
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String name = user.getName();
        String phone = user.getPhone();
        String email = user.getEmail();
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

        user.setPassword(encodedPassword);
        user.setSalt(salt);
        user.setEnabled(true);
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        iUserService.add(user);
        return ResultFactory.buildSuccessResult(user);
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
    public String authentication() {
        return "身份认证成功";
    }
}
