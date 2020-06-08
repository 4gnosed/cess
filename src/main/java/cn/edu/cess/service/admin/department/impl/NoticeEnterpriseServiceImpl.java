package cn.edu.cess.service.admin.department.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.admin.department.Notice;
import cn.edu.cess.entity.admin.department.NoticeEnterprise;
import cn.edu.cess.mapper.admin.department.NoticeEnterpriseMapper;
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
public class NoticeEnterpriseServiceImpl extends ServiceImpl<NoticeEnterpriseMapper, NoticeEnterprise> implements INoticeEnterpriseService {

    @Autowired
    INoticeService iNoticeService;

    @Override
    public Notice getByEId(Integer enterpriseId) {
        NoticeEnterprise noticeEnterprise = getNoticeEnterprise(enterpriseId);
        if (noticeEnterprise == null) return null;
        Notice notice = iNoticeService.getById(noticeEnterprise.getNid());
        return notice;
    }

    public NoticeEnterprise getNoticeEnterprise(Integer enterpriseId) {
        QueryWrapper<NoticeEnterprise> q = new QueryWrapper<>();
        q.eq(Constant.EID, enterpriseId).last("LIMIT 1");
        NoticeEnterprise noticeEnterprise = getOne(q);
        if (noticeEnterprise == null) return null;
        return noticeEnterprise;
    }

    @Override
    public boolean saveNotice(Notice notice, Integer enterpriseId) {
        NoticeEnterprise noticeEnterprise = new NoticeEnterprise();
        noticeEnterprise.setEid(enterpriseId);
        save(noticeEnterprise);
        notice.setId(noticeEnterprise.getNid());
        iNoticeService.fillData(notice);
        iNoticeService.save(notice);
        return true;
    }

    @Override
    public Integer getEnterpriseId(String noticeId) {
        QueryWrapper<NoticeEnterprise> q = new QueryWrapper<>();
        q.eq(Constant.NID, noticeId).last("LIMIT 1");
        NoticeEnterprise noticeEnterprise = getOne(q);
        if (noticeEnterprise == null) return null;
        return noticeEnterprise.getEid();
    }
}
