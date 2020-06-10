package cn.edu.cess.service.impl;


import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.Vo.AdminUserDto;
import cn.edu.cess.entity.User;
import cn.edu.cess.entity.admin.AdminRole;
import cn.edu.cess.entity.admin.AdminUserRole;
import cn.edu.cess.entity.content.enterprise.UserEnterprise;
import cn.edu.cess.entity.content.student.UserResume;
import cn.edu.cess.mapper.UserMapper;
import cn.edu.cess.service.IUserService;
import cn.edu.cess.service.admin.IAdminRoleService;
import cn.edu.cess.service.admin.IAdminUserRoleService;
import cn.edu.cess.service.content.enterprise.IUserEnterpriseService;
import cn.edu.cess.service.content.student.IUserResumeService;
import cn.edu.cess.service.content.student.IUserStudentService;
import cn.edu.cess.util.DateTimeUtils;
import cn.edu.cess.util.FileUploadUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    IAdminUserRoleService iAdminUserRoleService;
    @Autowired
    IAdminRoleService iAdminRoleService;
    @Autowired
    IUserEnterpriseService iUserEnterpriseService;
    @Autowired
    IUserResumeService iUserResumeService;
    @Autowired
    IUserStudentService iUserStudentService;

    @Override
    public User list(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.USERNAME, username).eq(Constant.PASSWORD, password);
        return getUser(username, queryWrapper);
    }

    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq(Constant.USERNAME, username);
        return getUser(username, queryWrapper);
    }

    @Override
    public int add(User user) {
        save(user);
        User userSaved = getByUsername(user.getUsername());
        return userSaved.getId();
    }

    private User getUser(String username, QueryWrapper<User> queryWrapper) {
        List<User> list = list(queryWrapper);
        if (list == null || list.size() == 0) {
            log.info("没有该用户：" + username);
            return null;
        }
        if (list.size() > 1) {
            log.error("getByName获取到多个User，username={}", username);
        }
        return list.get(0);
    }

    @Override
    public boolean isExist(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.USERNAME, username);
        List<User> list = list(queryWrapper);
        if (list.size() != 0) {
            return true;
        }
        return false;
    }

    @Override
    public void updateLastLogin(String username) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(Constant.USERNAME, username).set(Constant.LAST_LOGIN, DateTimeUtils.getSystemTime());
        update(updateWrapper);
    }

    @Override
    public String resetPassword(String username) {
        User user = getByUsername(username);
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String encodedPassword = new SimpleHash(Constant.MD_5, Constant.DEFAULT_PASSWORD, salt, Constant.HASH_ITERATIONS).toString();
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        if (saveOrUpdate(user)) {
            return Constant.DEFAULT_PASSWORD;
        }
        return null;
    }

    @Override
    public boolean editUser(AdminUserDto adminUserDto) {
        User user = adminUserDto.getUser();
        boolean b1 = saveOrUpdate(user);
        boolean b2 = iAdminUserRoleService.changeUserRole(adminUserDto.getRoles(), user.getId());
        return b1 && b2;
    }

    @Override
    public List<AdminUserDto> listAll() {
        List<User> userList = list();
        int size = userList.size();
        ArrayList<AdminUserDto> adminUserDtoList = new ArrayList<>(size);
        AdminUserDto adminUserDto = null;
        for (int i = 0; i < size; i++) {
            adminUserDto = new AdminUserDto();
            User user = userList.get(i);
            adminUserDto.setUser(user);
            adminUserDto.setRoles(iAdminRoleService.listRoleByUsername(user.getUsername()));
            adminUserDtoList.add(adminUserDto);
        }
        return adminUserDtoList;
    }

    @Transactional
    @Override
    public boolean removeUser(int userId) {
        boolean b1 = removeById(userId);
        //删除关联角色记录
        QueryWrapper<AdminUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.UID, userId);
        boolean b2 = iAdminUserRoleService.remove(queryWrapper);
        //删除关联学生记录
        boolean b3 = iUserEnterpriseService.removeByUid(userId);
        //删除关联企业记录
        boolean b4 = iUserStudentService.removeByUid(userId);
        return b1 && b2 && (b3 || b4);
    }

    @Override
    public boolean removeUsers(List<User> userList) {
        for (User user : userList) {
            if (!removeUser(user.getId()))
                return false;
        }
        return true;
    }

    @Override
    public boolean updateUserStatus(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(Constant.USERNAME, user.getUsername()).set(Constant.ENABLED, user.getEnabled());
        return update(updateWrapper);
    }

    @Override
    public boolean isEnable(String username) {
        return getByUsername(username).getEnabled();
    }

    @Override
    public void updatePassword(String salt, String username, String newPassword) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(Constant.USERNAME, username).set(Constant.PASSWORD, newPassword).set(Constant.SALT, salt);
        update(updateWrapper);
    }

    @Override
    public User getByEid(int eid) {
        UserEnterprise userEnterprise = iUserEnterpriseService.getByEid(eid);
        return getById(userEnterprise.getUid());
    }

    @Override
    public User getByRid(int rid) {
        UserResume userResume = iUserResumeService.getByRid(rid);
        return getById(userResume.getUid());
    }

    @Override
    public boolean saveAvatarPath(String filePath, Integer userId) {
        UpdateWrapper<User> u = new UpdateWrapper<>();
        u.eq(Constant.ID, userId).set(Constant.AVATAR_PATH, filePath);
        return update(u);
    }

    @Override
    public User fillUser(HttpServletRequest request, String username, Integer userId) {
        User user = null;
        if (username != null && userId == null) {
            user = getByUsername(username);
        } else if (username == null && userId != null) {
            user = getById(userId);
        }
        AdminRole role = iAdminRoleService.listRoleByUserId(user.getId()).get(0);
        user.setPassword("");
        user.setSalt("");
        user.setRole(role.getNameZh());
        user.setRoleId(role.getId());
        user.setAvatarPath(FileUploadUtil.getIpPort(request) + user.getAvatarPath());
        return user;
    }
}
