package cn.edu.cess.entity.Vo;

import lombok.Data;

/**
 * @Author Gnosed Lu
 * @Date 2020/4/13
 * @Description
 */
@Data
public class UserVo {
    String username;
    String oldPassword;
    String newPassword;
}
