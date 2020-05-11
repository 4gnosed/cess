package cn.edu.cess.service.admin.department;

import cn.edu.cess.entity.admin.department.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-11
 */
public interface INoticeService extends IService<Notice> {

    boolean saveNotice(Notice notice);
}
