package cn.edu.cess.controller;


import cn.edu.cess.entity.common.Message;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.common.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.edu.cess.base.AbstractClass;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/api/message")
public class MessageController extends AbstractClass {

    @Autowired
    IMessageService iMessageService;

    @GetMapping("")
    public Result getAll(@RequestParam("") Integer userId) {
        return ResultFactory.buildSuccessResult(iMessageService.getByUserId(userId));
    }

    @PostMapping("")
    public Result add(@RequestBody Message message) {
        iMessageService.sendMessage(message);
        return ResultFactory.buildSuccessResult("");
    }

    @PutMapping("/status")
    public Result changeStatusToRead(@RequestBody List<Integer> selectedMegsId) {
        iMessageService.changeStatusToRead(selectedMegsId);
        return ResultFactory.buildSuccessResult("");
    }

    @GetMapping("/new")
    public Result getNewMessageNumber(@RequestParam("") Integer userId) {
        int newMessageNum = iMessageService.getNewMessageNumber(userId);
        if (newMessageNum == 0) {
            return ResultFactory.buildEmptyResult("没有未读消息");
        } else {
            return ResultFactory.buildSuccessResult(newMessageNum);
        }
    }
    @GetMapping("/send")
    public Result getSendedMessages(@RequestParam("") Integer userId) {
        return  ResultFactory.buildSuccessResult(iMessageService.getSendedMessages(userId));
    }
}
