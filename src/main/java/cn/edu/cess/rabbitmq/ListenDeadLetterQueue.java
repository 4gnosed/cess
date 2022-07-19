package cn.edu.cess.rabbitmq;

import cn.edu.cess.constant.MqConstant;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ListenDeadLetterQueue {

    @RabbitListener(queues = MqConstant.DEAD_LETTER_QUEUE)
    public void receiveMessage(Message msg) {
        log.info("ListenDeadLetterQueue接收消息：{}", JSON.toJSONString(msg));
    }

}
