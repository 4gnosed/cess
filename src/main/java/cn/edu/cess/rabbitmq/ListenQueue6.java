package cn.edu.cess.rabbitmq;

import cn.edu.cess.constant.MqConstant;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ListenQueue6 {

    @RabbitListener(queues = MqConstant.QUEUE6,concurrency = "3-6",containerFactory = "simpleMessageListenerContainer")
    public void receiveMessage(Object o) {
        log.info("ListenQueue6接收消息：{}", JSON.toJSONString(o));
    }

}
