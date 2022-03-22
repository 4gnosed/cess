package cn.edu.cess.rabbitmq;

import cn.edu.cess.constant.MqConstant;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Package: cn.edu.cess.rabbitmq
 * @Description:消费者
 * @Author: LuDeSong
 * @Date: 2021-8-4 19:10
 */
@Component
@Slf4j
@RabbitListener(queues = MqConstant.QUEUE1)
public class ListenQueue1Other {

    @RabbitHandler
    public void processHandler(Object msg, Channel channel, Message message) throws IOException {

        try {
            log.info("接收消息：{}", JSON.toJSONString(msg));

            //TODO 具体业务

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        } catch (Exception e) {

            if (message.getMessageProperties().getRedelivered()) {

                log.error("消息已重复处理失败,拒绝再次接收...");

                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {

                log.error("消息即将再次返回队列处理...");

                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

}
