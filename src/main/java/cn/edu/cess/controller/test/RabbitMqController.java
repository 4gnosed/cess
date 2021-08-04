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

    @RequestMapping("/sendMessage")
    public Result sendMessage(@RequestParam String message) {
        rabbitProducer.sendMessage(message);
        return ResultFactory.buildSuccessResult();
    }
}
