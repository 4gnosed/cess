package cn.edu.cess.config;

import cn.edu.cess.constant.MqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Package: cn.edu.cess.config
 * @Description:
 * @Author: LuDeSong
 * @Date: 2021-8-4 19:26
 */
@Configuration
public class RabbitMqConfig {
    /**
     * 定义demoQueue队列
     * @return
     */
    @Bean
    public Queue demoQueue() {
        return new Queue(MqConstant.DEMO_QUEUE);
    }

    /**
     * 定义交换机
     * @return
     */
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(MqConstant.DIRECT_EXCHANGE_1,true,false);
    }

    /**
     * 将队列和交换机绑定, 并设置用于匹配键
     * @return
     */
    @Bean
    Binding binding(){
        return BindingBuilder.bind(demoQueue()).to(directExchange()).with(MqConstant.ROUTING_KEY_1);
    }
}
