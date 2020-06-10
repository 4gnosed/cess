package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.student.UserStudent;
import cn.edu.cess.mapper.content.student.UserStudentMapper;
import cn.edu.cess.service.content.student.IUserStudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-09
 */
@Service
public class UserStudentServiceImpl extends ServiceImpl<UserStudentMapper, UserStudent> implements IUserStudentService {

    @Override
    public UserStudent getByUid(int id) {
        QueryWrapper<UserStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.UID, id);
        return getOne(queryWrapper);
    }

    @Override
    public List<UserStudent> getUserStudentsByPage(Integer page, Integer size) {
        Page<UserStudent> userStudentPage = page(new Page<>(page, size));
        List<UserStudent> userStudentList = userStudentPage.getRecords();
        return userStudentList;
    }

    @Override
    public void updateStatus(boolean enabled, String id) {
        UpdateWrapper<UserStudent> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(Constant.ID, id).set(Constant.ENABLED, enabled);
        update(updateWrapper);
    }

    @Override
    public boolean removeByUid(int userId) {
        QueryWrapper<UserStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.UID, userId);
        return remove(queryWrapper);
    }
}
