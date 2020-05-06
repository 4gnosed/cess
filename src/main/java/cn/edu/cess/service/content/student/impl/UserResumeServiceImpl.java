package cn.edu.cess.service.content.student.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.UserEnterprise;
import cn.edu.cess.entity.content.student.UserResume;
import cn.edu.cess.mapper.content.student.UserResumeMapper;
import cn.edu.cess.service.content.student.IUserResumeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-09
 */
@Service
public class UserResumeServiceImpl extends ServiceImpl<UserResumeMapper, UserResume> implements IUserResumeService {

    @Override
    public UserResume getByRid(int rid) {
        QueryWrapper<UserResume> q = new QueryWrapper<>();
        q.eq(Constant.RID, rid).last("LIMIT 1");
        return getOne(q);
    }
}
