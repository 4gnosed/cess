package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.content.enterprise.Enterprise;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-12
 */
public interface IEnterpriseService extends IService<Enterprise> {

    void fillData(Enterprise enterprise);

    void updateEnterprise(Enterprise enterprise);

    Integer getLastId();

    Enterprise getByName(String name);
}
