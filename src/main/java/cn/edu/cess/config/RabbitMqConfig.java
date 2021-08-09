package cn.edu.cess.config;

import cn.edu.cess.constant.MqConstant;
import org.springframework.amqp.core.*;
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

    @Bean
    public Queue queue1() {
        return new Queue(MqConstant.QUEUE1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(MqConstant.QUEUE2);
    }

    @Bean
    public Queue queue3() {
        return new Queue(MqConstant.QUEUE3);
    }

    @Bean
    public Queue queue4() {
        return new Queue(MqConstant.QUEUE4);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(MqConstant.DIRECT_EXCHANGE_1, true, false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(MqConstant.TOPIC_EXCHANGE_1, true, false);
    }

    @Bean
    Binding binding1() {
        return BindingBuilder.bind(queue1()).to(directExchange()).with(MqConstant.ROUTING_KEY);
    }

    @Bean
    Binding binding2() {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with(MqConstant.ROUTING_KEY_STAR);
    }

    @Bean
    Binding binding3() {
        return BindingBuilder.bind(queue3()).to(topicExchange()).with(MqConstant.ROUTING_KEY_WELL);
    }

    @Bean
    Binding binding4() {
        return BindingBuilder.bind(queue4()).to(topicExchange()).with(MqConstant.ROUTING_KEY);
    }

}
