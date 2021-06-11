package cn.edu.cess.service.common.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.common.Message;
import cn.edu.cess.entity.common.MessagePositions;
import cn.edu.cess.entity.common.User;
import cn.edu.cess.entity.content.enterprise.Enterprise;
import cn.edu.cess.entity.content.enterprise.Positions;
import cn.edu.cess.mapper.common.MessageMapper;
import cn.edu.cess.service.common.IMessagePositionsService;
import cn.edu.cess.service.common.IMessageService;
import cn.edu.cess.service.common.IUserService;
import cn.edu.cess.service.content.enterprise.IEnterpriseService;
import cn.edu.cess.service.content.enterprise.IPositionsService;
import cn.edu.cess.util.DateTimeUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-27
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Autowired
    IUserService iUserService;
    @Autowired
    IPositionsService iPositionsService;
    @Autowired
    IMessagePositionsService iMessagePositionsService;
    @Autowired
    IEnterpriseService iEnterpriseService;

    @Override
    public List<Message> getByUserId(Integer userId) {
        QueryWrapper<Message> q = new QueryWrapper<>();
        q.eq(Constant.RECEIVER_UID, userId);
        return list(q);
    }

    @Override
    public void changeStatusToRead(List<Integer> messageIds) {
        UpdateWrapper<Message> u = new UpdateWrapper<>();
        for (Integer messageId : messageIds) {
            u.clear();
            u.eq(Constant.ID, messageId).set(Constant.STATUS, true);
            update(u);
        }
    }

    @Override
    public void sendMessage(Message message) {
        message.setSendTime(DateTimeUtils.getCurrentTime());
        message.setStatus(false);
        save(message);
    }

    @Override
    public int getNewMessageNumber(Integer userId) {
        QueryWrapper<Message> q = new QueryWrapper<>();
        q.eq(Constant.RECEIVER_UID, userId).eq(Constant.STATUS, false);
        return count(q);
    }

    @Override
    public List<Message> getSendedMessages(Integer userId) {
        QueryWrapper<Message> q = new QueryWrapper<>();
        q.eq(Constant.SENDER_UID, userId);
        return list(q);
    }

    /**
     * 消息唯一性有主题title决定，值为[学生姓名-说明文字-职位名称]
     *
     * @param userId
     * @param positionId
     * @return
     */
    @Override
    public boolean sendMessage(Integer userId, Integer positionId) {
        //获取发送者(学生)
        User sender = iUserService.getById(userId);
        String senderName = sender.getName();
        int senderUid = sender.getId();
        //获取相关的职位
        Positions positions = iPositionsService.getById(positionId);
        String positionsName = positions.getName();
        //判断唯一性，是否投递过
        String title = senderName + Constant.SEND_RESUME_TITLE + positionsName;
        if (getByTitle(title) != null) {
            return true;
        }
        //获取接收者(企业)
        Integer uid = iPositionsService.getUserIdByPid(positionId);
        User receiver = iUserService.getById(uid);
        //构建保存消息
        Message message = new Message();
        message.setSender(senderName);
        message.setSenderUid(senderUid);
        message.setReceiver(receiver.getName());
        message.setReceiverUid(receiver.getId());
        message.setSendTime(DateTimeUtils.getCurrentTime());
        message.setTitle(title);
        message.setContent(message.getSender() + Constant.SEND_RESUME_CONTENT);
        message.setStatus(false);
        save(message);
        //保存消息-职位的多对多关系
        MessagePositions messagePositions = new MessagePositions();
        messagePositions.setMid(getByTitle(message.getTitle()).getId());
        messagePositions.setPid(positionId);
        iMessagePositionsService.save(messagePositions);
        return false;
    }

    @Override
    public void sendMessage(int eid, boolean enabled, String username) {
        User suser = iUserService.getByUsername(username);
        Enterprise enterprise = iEnterpriseService.getById(eid);
        User ruser = iUserService.getByEid(eid);
        Message message = new Message();
        message.setSender(suser.getName());
        message.setSenderUid(suser.getId());
        message.setReceiver(ruser.getName());
        message.setReceiverUid(ruser.getId());
        String result = "";
        if (enabled) {
            result = "已通过";
            message.setContent(Constant.SEND_PASS_CHECK_CONTENT);
        } else {
            result = "未通过";
            message.setContent(Constant.SEND_NO_PASS_CHECK_CONTENT);
        }
        message.setTitle(enterprise.getName() + Constant.SEND_CHECK_TITLE + result);
        message.setSendTime(DateTimeUtils.getCurrentTime());
        save(message);
    }

    public Message getByTitle(String title) {
        QueryWrapper<Message> q = new QueryWrapper<>();
        q.eq(Constant.TITLE, title).last("LIMIT 1");
        return getOne(q);
    }
}
