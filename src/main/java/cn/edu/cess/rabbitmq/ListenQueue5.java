package cn.edu.cess.rabbitmq;

import cn.edu.cess.constant.MqConstant;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Package: cn.edu.cess.rabbitmq
 * @Description:消费者
 * @Author: LuDeSong
 * @Date: 2021-8-4 19:10
 */
@Component
@Slf4j
public class ListenQueue5 {

    @RabbitListener(bindings = {
            @QueueBinding(value = @Queue(value = MqConstant.QUEUE5), exchange = @Exchange(name = MqConstant.FANOUT_EXCHANGE_1, type = ExchangeTypes.FANOUT))
    })
    public void receiveMessage(Object o) {
        log.info("接收消息：{}", JSON.toJSONString(o));
    }

}
