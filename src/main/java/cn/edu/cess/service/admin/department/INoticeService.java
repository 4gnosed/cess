package cn.edu.cess.service.admin.department;

import cn.edu.cess.entity.admin.department.Notice;
import cn.edu.cess.result.ResultPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-11
 */
public interface INoticeService extends IService<Notice> {

    boolean saveNotice(Notice notice);

    void fillData(Notice notice, String schoolNoticeType);

    boolean deleteNotices(List<Notice> notices);

    ResultPage listByPage(Integer page, Integer size);

    boolean updateNotice(Notice notice);
}
