package cn.edu.cess.rabbitmq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Package: cn.edu.cess.rabbitmq
 * @Description:消息者
 * @Author: LuDeSong
 * @Date: 2021-8-4 19:10
 */
@Component
@Slf4j
@RabbitListener(queues = "demoQueue")
public class RabbitConsumer {

    @RabbitHandler
    public void receiveMessage(Object o) {
        log.info("接收消息：{}", JSON.toJSONString(o));
    }

}
