package cn.edu.cess.mapper.common;


import cn.edu.cess.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

public interface UserMapper extends BaseMapper<User> {
}
