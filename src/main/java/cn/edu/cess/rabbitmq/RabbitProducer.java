package cn.edu.cess.rabbitmq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Package: cn.edu.cess.rabbitmq
 * @Description:生产者
 * @Author: LuDeSong
 * @Date: 2021-8-4 19:10
 */
@Component
@Slf4j
public class RabbitProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Object o) {
        log.info("发送消息：{}", JSON.toJSONString(o));
        amqpTemplate.convertAndSend("demoQueue", JSON.toJSONString(o));
    }
}
