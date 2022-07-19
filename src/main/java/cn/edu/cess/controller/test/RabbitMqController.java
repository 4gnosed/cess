package cn.edu.cess.controller.test;

import cn.edu.cess.rabbitmq.RabbitProducer;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: cn.edu.cess.controller.test
 * @Description:rabbitmq测试接口
 * @Author: LuDeSong
 * @Date: 2021-8-4 19:01
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {

    @Autowired
    RabbitProducer rabbitProducer;

    @RequestMapping("/sendDirectMessage")
    public Result sendDirectMessage(@RequestParam String message) {
        rabbitProducer.sendDirectMessage(message);
        return ResultFactory.buildSuccessResult();
    }

    @RequestMapping("/sendTopicMessage1")
    public Result sendTopicMessage1(@RequestParam String message) {
        rabbitProducer.sendTopicMessage1(message);
        return ResultFactory.buildSuccessResult();
    }

    @RequestMapping("/sendTopicMessage2")
    public Result sendTopicMessage2(@RequestParam String message) {
        rabbitProducer.sendTopicMessage2(message);
        return ResultFactory.buildSuccessResult();
    }

    @RequestMapping("/sendTopicMessage3")
    public Result sendTopicMessage3(@RequestParam String message) {
        rabbitProducer.sendTopicMessage3(message);
        return ResultFactory.buildSuccessResult();
    }

    @RequestMapping("/sendFanoutMessage")
    public Result sendFanoutMessage(@RequestParam String message) {
        rabbitProducer.sendFanoutMessage(message);
        return ResultFactory.buildSuccessResult();
    }

    @RequestMapping("/sendExpirationMessage")
    public Result sendExpirationMessage(@RequestParam String message,@RequestParam String milliSeconds) {
        rabbitProducer.sendExpirationMessage(message,milliSeconds);
        return ResultFactory.buildSuccessResult();
    }


    @RequestMapping("/sendDelayMessage")
    public Result sendDelayMessage(@RequestParam String message,@RequestParam String milliSeconds) {
        rabbitProducer.sendDelayMessage(message,milliSeconds);
        return ResultFactory.buildSuccessResult();
    }
}
