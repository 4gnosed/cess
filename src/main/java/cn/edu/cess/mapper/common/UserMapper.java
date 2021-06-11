package cn.edu.cess.mapper.common;


import cn.edu.cess.entity.common.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    List<User> queryEnableUser();
}
