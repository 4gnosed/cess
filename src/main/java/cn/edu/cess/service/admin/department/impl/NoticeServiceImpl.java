package cn.edu.cess.service.admin.department.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.admin.department.Notice;
import cn.edu.cess.mapper.admin.department.NoticeMapper;
import cn.edu.cess.service.admin.department.INoticeEnterpriseService;
import cn.edu.cess.service.admin.department.INoticeService;
import cn.edu.cess.util.DateTimeUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-11
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Autowired
    INoticeEnterpriseService iNoticeEnterpriseService;

    @Override
    public boolean saveNotice(Notice notice) {
        return save(notice);
    }

    @Override
    public void fillData(Notice notice, String type) {
        Subject subject = SecurityUtils.getSubject();
        String userName = (String) subject.getPrincipal();
        notice.setPublisher(userName.toString());
        notice.setNoticeType(type);
        notice.setTime(DateTimeUtils.getCurrentTime());
    }

    @Override
    public boolean deleteNotices(List<Notice> notices) {
        for (Notice notice : notices) {
            if (!removeById(notice.getId())) {
                return false;
            }
        }
        return true;
    }

    private Notice getByTitleAndTime(Notice notice) {
        QueryWrapper<Notice> q = new QueryWrapper<>();
        q.eq(Constant.TITLE, notice.getTitle()).eq(Constant.TIME, notice.getTime());
        return getOne(q);
    }
}
