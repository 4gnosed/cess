package cn.edu.cess.service;


import cn.edu.cess.dto.AdminUserDto;
import cn.edu.cess.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IUserService extends IService<User> {
    User list(String username, String password);

    User getByName(String username) ;

    int add(User user);

    boolean isExist(String username);

    void updateLastLogin(String username);

    String resetPassword(String username);

    boolean editUser(AdminUserDto adminUserDto);

    List<AdminUserDto> listAll();

    boolean removeUser(int userId);

    boolean removeUsers(List<User> userList);

    boolean updateUserStatus(User user);

    boolean isEnable(String username);
}
