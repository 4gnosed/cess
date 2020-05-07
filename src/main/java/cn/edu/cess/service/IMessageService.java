package cn.edu.cess.service;

import cn.edu.cess.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-27
 */
public interface IMessageService extends IService<Message> {

    List<Message> getByUserId(Integer userId);

    void changeStatusToRead(List<Integer> messageIds);

    void sendMessage(Message message);

    int getNewMessageNumber(Integer userId);

    List<Message> getSendedMessages(Integer userId);

    boolean sendMessage(Integer userId, Integer positionId);

    void sendMessage(int eid, boolean enabled, String username);
}
