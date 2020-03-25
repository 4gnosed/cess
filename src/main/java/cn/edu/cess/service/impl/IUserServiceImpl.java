package cn.edu.cess.service.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.mapper.UserMapper;
import cn.edu.cess.entity.User;
import cn.edu.cess.service.IUserService;
import cn.edu.cess.util.ExceptionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User list(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.USERNAME, username).eq(Constant.PASSWORD, password);
        return getUser(username, queryWrapper);
    }

    @Override
    public User getByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq(Constant.USERNAME, username);
        return getUser(username, queryWrapper);
    }

    @Override
    public boolean add(User user) {
        ExceptionUtil.notNull(user, "User must be not null");
        String username = user.getUsername();
        if (getByName(username) == null) {
            return save(user);
        }
        return false;
    }

    private User getUser(String username, QueryWrapper<User> queryWrapper) {
        List<User> list = list(queryWrapper);
        if (list == null || list.size() == 0) {
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
        updateWrapper.eq(Constant.USERNAME, username).set(Constant.LAST_LOGIN, new Date());
        update(updateWrapper);
    }
}
