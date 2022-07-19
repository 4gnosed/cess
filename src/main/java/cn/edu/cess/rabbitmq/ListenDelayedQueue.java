package cn.edu.cess.rabbitmq;

import cn.edu.cess.constant.MqConstant;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ListenDelayedQueue {

    @RabbitListener(queues = MqConstant.DELAYED_QUEUE)
    public void receiveMessage(Message msg) {
        log.info("ListenDelayedQueue接收消息：{}", JSON.toJSONString(msg));
    }

}
