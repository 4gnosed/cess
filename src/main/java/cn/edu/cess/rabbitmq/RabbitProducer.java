package cn.edu.cess.rabbitmq;

import cn.edu.cess.constant.MqConstant;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageProperties;
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

    public void sendDirectMessage(Object o) {
        log.info("sendDirectMessage发送消息：{}", JSON.toJSONString(o));
        amqpTemplate.convertAndSend(MqConstant.DIRECT_EXCHANGE_1, MqConstant.ROUTING_KEY, o);
    }

    public void sendTopicMessage1(Object o) {
        log.info("sendTopicMessage1发送消息：{}", JSON.toJSONString(o));
        amqpTemplate.convertAndSend(MqConstant.TOPIC_EXCHANGE_1, MqConstant.ROUTING_KEY, o);
    }

    public void sendTopicMessage2(Object o) {
        log.info("sendTopicMessage2发送消息：{}", JSON.toJSONString(o));
        amqpTemplate.convertAndSend(MqConstant.TOPIC_EXCHANGE_1, "top.fjda", o);
    }

    public void sendTopicMessage3(Object o) {
        log.info("sendTopicMessage3发送消息：{}", JSON.toJSONString(o));
        amqpTemplate.convertAndSend(MqConstant.TOPIC_EXCHANGE_1, "top.fj.jkfd", o);
    }

    public void sendFanoutMessage(Object o) {
        log.info("sendFanoutMessage发送消息：{}", JSON.toJSONString(o));
        amqpTemplate.convertAndSend(MqConstant.FANOUT_EXCHANGE_1, null, o);
    }

    public void sendExpirationMessage(Object o, String milliSeconds) {
        log.info("sendDirectMessage发送消息：{}", JSON.toJSONString(o));
        amqpTemplate.convertAndSend(MqConstant.DIRECT_EXCHANGE_2, MqConstant.ROUTING_KEY_2, o, msg->{
            msg.getMessageProperties().setExpiration(milliSeconds);
            return msg;
        });
    }


    public void sendDelayMessage(Object o, String milliSeconds){
        log.info("sendDelayMessage发送消息：{}", JSON.toJSONString(o));
        amqpTemplate.convertAndSend(MqConstant.DELAYED_DIRECT_EXCHANGE,MqConstant.DELAYED_ROUTING_KEY,o,msg->{
            msg.getMessageProperties().setExpiration(milliSeconds);
            return msg;
        });
    }

}
