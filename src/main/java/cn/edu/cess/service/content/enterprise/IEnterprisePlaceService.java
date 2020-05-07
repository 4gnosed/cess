package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.content.enterprise.EnterprisePlace;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-03
 */
public interface IEnterprisePlaceService extends IService<EnterprisePlace> {

    List<EnterprisePlace> getByTalkDate(String talkDate);

    EnterprisePlace getByEid(Integer id);

    boolean save(Integer eid, Integer pid, Integer talkTimeId);
}
