package cn.edu.cess.service.admin.department;

import cn.edu.cess.entity.admin.department.Notice;
import cn.edu.cess.entity.admin.department.NoticeEnterprise;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-11
 */
public interface INoticeEnterpriseService extends IService<NoticeEnterprise> {

    Notice getByEId(Integer enterpriseId);

    boolean saveNotice(Notice notice, Integer enterpriseId);
}
