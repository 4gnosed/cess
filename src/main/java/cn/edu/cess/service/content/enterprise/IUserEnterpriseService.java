package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.content.enterprise.UserEnterprise;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-09
 */
public interface IUserEnterpriseService extends IService<UserEnterprise> {

    List<UserEnterprise> getUserEnterprisesByPage(Integer page, Integer size);

    void updateStatus(boolean enabled, String id);

    UserEnterprise getByUid(Integer userId);

    UserEnterprise getByEid(Integer eid);

    int getTotal();

    boolean removeByUid(int userId);
}
