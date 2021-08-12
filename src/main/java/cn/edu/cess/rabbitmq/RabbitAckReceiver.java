package cn.edu.cess.rabbitmq;

import cn.edu.cess.constant.SysRetEnum;
import cn.edu.cess.exception.CustomException;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Package: cn.edu.cess.rabbitmq
 * @Description:消费者手动确认消息监听器
 * @Author: LuDeSong
 * @Date: 2021-8-12 15:05
 */
@Slf4j
@Component
public class RabbitAckReceiver implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String msg = new String(message.getBody());
            log.info("消费者手动确认消息来自队列：{}", message.getMessageProperties().getConsumerQueue());
            log.info("消费者手动确认消息内容：{}", msg);
            if ("basicRejectRequeue".equals(msg)) {
                channel.basicReject(deliveryTag, true);//第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
            } else if ("basicNack".equals(msg)) {
                channel.basicNack(deliveryTag, true, false);
            } else if ("basicReject".equals(msg)) {
                throw new CustomException(SysRetEnum.DEFAULT_EXCEPTION);//抛异常
            } else {
                channel.basicAck(deliveryTag, true); //第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            }
        } catch (Exception e) {
            log.error("", e);
            channel.basicReject(deliveryTag, false);
        }
    }
}