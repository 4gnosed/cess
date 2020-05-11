package cn.edu.cess.service.admin.department.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.admin.department.Notice;
import cn.edu.cess.mapper.admin.department.NoticeMapper;
import cn.edu.cess.service.admin.department.INoticeEnterpriseService;
import cn.edu.cess.service.admin.department.INoticeService;
import cn.edu.cess.util.DateTimeUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        notice.setNoticeType(Constant.SCHOOL_NOTICE_TYPE);
        notice.setTime(DateTimeUtils.getCurrentTime());
        return save(notice);
    }

    private Notice getByTitleAndTime(Notice notice) {
        QueryWrapper<Notice> q = new QueryWrapper<>();
        q.eq(Constant.TITLE, notice.getTitle()).eq(Constant.TIME, notice.getTime());
        return getOne(q);
    }
}
