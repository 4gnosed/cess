package cn.edu.cess.config;

import cn.edu.cess.constant.MqConstant;
import cn.edu.cess.constant.MqQueueEnum;
//import cn.edu.cess.rabbitmq.RabbitAckReceiver;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static cn.edu.cess.constant.MqQueueEnum.*;

/**
 * @Package: cn.edu.cess.config
 * @Description:
 * @Author: LuDeSong
 * @Date: 2021-8-4 19:26
 */
@Configuration
public class RabbitMqConfig {

    // 生产发送消息确认机制
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        //确认消息是否到达exchange，已实现方法confirm中ack属性为标准，true到达，反之进入黑洞
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("ConfirmCallback:     " + "相关数据：" + correlationData);
            System.out.println("ConfirmCallback:     " + "确认情况：" + ack);
            System.out.println("ConfirmCallback:     " + "原因：" + cause);
        });

        //消息没有正确到达队列时触发回调，如果正确到达队列不执行
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("ReturnCallback:     " + "消息：" + message);
            System.out.println("ReturnCallback:     " + "回应码：" + replyCode);
            System.out.println("ReturnCallback:     " + "回应信息：" + replyText);
            System.out.println("ReturnCallback:     " + "交换机：" + exchange);
            System.out.println("ReturnCallback:     " + "路由键：" + routingKey);
        });

        return rabbitTemplate;
    }

    //消费者消息手动确认
//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(@Autowired CachingConnectionFactory cachingConnectionFactory,
//                                                                         @Autowired RabbitAckReceiver rabbitAckReceiver) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
//        container.setConcurrentConsumers(1);
//        container.setMaxConcurrentConsumers(1);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // RabbitMQ默认是自动确认，这里改为手动确认消息
//        //监听多个队列(不监听QUEUE1)
//        String[] queues = Arrays.asList(values()).stream().filter(o -> !o.getQueue().equals(QUEUE1.getQueue()))
//                .map(MqQueueEnum::getQueue).toArray(String[]::new);
//        container.addQueueNames(queues);
//        container.setMessageListener(rabbitAckReceiver);
//        return container;
//    }

    @Bean
    public Queue queue1() {
        return new Queue(QUEUE1.getQueue());
    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE2.getQueue());
    }

    @Bean
    public Queue queue3() {
        return new Queue(QUEUE3.getQueue());
    }

    @Bean
    public Queue queue4() {
        return new Queue(QUEUE4.getQueue());
    }

    @Bean
    public Queue queue5() {
        return new Queue(QUEUE5.getQueue());
    }

    /**
     * 发送到QUEUE6的消息有三种情况会变成死信，由死信队列DEAD_LETTER_QUEUE消费：
     * （1）消息被拒绝（basic.reject 或者 basic.nack），并且requeue=false;
     * （2）消息的过期时间到期了；
     * （3）队列长度限制超过了。
     * @return
     */
    @Bean
    public Queue queue6() {
        return QueueBuilder.durable(QUEUE6.getQueue())
                .withArgument("x-dead-letter-exchange",MqConstant.DEAD_LETTER_DIRECT_EXCHANGE)
                .withArgument("x-dead-letter-routing-key",MqConstant.DEAD_LETTER_ROUTING_KEY)
                .build();
    }

    /**
     * 死信队列
     * @return
     */
    @Bean
    public Queue deadLetterQueue(){
        return new Queue(DEAD_LETTER_QUEUE.getQueue());
    }

    /**
     * x-delayed-message 插件延迟队列
     * @return
     */
    @Bean
    public Queue delayedQueue(){
        return new Queue(DELAYED_QUEUE.getQueue());
    }


    @Bean
    DirectExchange directExchange1() {
        return new DirectExchange(MqConstant.DIRECT_EXCHANGE_1, true, false);
    }

    @Bean
    DirectExchange directExchange2() {
        return new DirectExchange(MqConstant.DIRECT_EXCHANGE_2, true, false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(MqConstant.TOPIC_EXCHANGE_1, true, false);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(MqConstant.FANOUT_EXCHANGE_1, true, false);
    }

    @Bean
    DirectExchange deadLetterExchange(){
        return new DirectExchange(MqConstant.DEAD_LETTER_DIRECT_EXCHANGE,true,false);
    }

    @Bean
    CustomExchange delayedExchange(){
        Map<String,Object> args = new HashMap<>();
        args.put("x-delayed-type","direct");
        return new CustomExchange(MqConstant.DELAYED_DIRECT_EXCHANGE,"x-delayed-message",true,false,args);
    }

    @Bean
    Binding binding1() {
        return BindingBuilder.bind(queue1()).to(directExchange1()).with(MqConstant.ROUTING_KEY);
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

    @Bean
    Binding binding5() {
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }

    @Bean
    Binding binding6() {
        return BindingBuilder.bind(queue2()).to(fanoutExchange());
    }

    @Bean
    Binding binding7() {
        return BindingBuilder.bind(queue3()).to(fanoutExchange());
    }

    @Bean
    Binding binding8() {
        return BindingBuilder.bind(queue4()).to(fanoutExchange());
    }

    @Bean
    Binding binding9() {
        return BindingBuilder.bind(queue5()).to(fanoutExchange());
    }

    @Bean
    Binding binding10(@Qualifier("queue6")Queue queue6,@Qualifier("directExchange2") DirectExchange directExchange2) {
        return BindingBuilder.bind(queue6).to(directExchange2).with(MqConstant.ROUTING_KEY_2);
    }

    @Bean
    Binding bindingDeadLetterQueue(@Qualifier("deadLetterQueue") Queue deadLetterQueue,@Qualifier("deadLetterExchange") DirectExchange deadLetterExchange ){
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(MqConstant.DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    Binding bindingDelayedQueue(@Qualifier("delayedQueue") Queue delayedQueue,@Qualifier("delayedExchange") CustomExchange delayedExchange){
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(MqConstant.DELAYED_ROUTING_KEY).noargs();
    }


}
