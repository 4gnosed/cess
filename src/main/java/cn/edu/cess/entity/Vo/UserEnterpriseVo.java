package cn.edu.cess.entity.Vo;

import cn.edu.cess.entity.common.User;
import cn.edu.cess.entity.content.enterprise.Enterprise;
import lombok.Data;

/**
 * @Author Gnosed Lu
 * @Date 2020/4/12
 * @Description
 */
@Data
public class UserEnterpriseVo {
    /**
     * 等同于userEnterprise的id
     */
    Integer id;
    User user;
    Enterprise enterprise;
    boolean enabled;
}
