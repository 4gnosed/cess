package cn.edu.cess.service;


import cn.edu.cess.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IUserService extends IService<User> {
    User list(String username, String password);

    User getByName(String username) ;

    boolean add(User user);

    boolean isExist(String username);

    void updateLastLogin(String username);
}
