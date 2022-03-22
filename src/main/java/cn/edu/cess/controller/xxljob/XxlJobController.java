package cn.edu.cess.controller.xxljob;

import cn.edu.cess.entity.xxljob.XxlJobInfo;
import cn.edu.cess.result.Result;
import cn.edu.cess.service.xxljob.XxlJobTriggerService;
import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/xxlJob")
public class XxlJobController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    XxlJobTriggerService xxlJobTriggerService;

    @RequestMapping("/addJob")
    @ResponseBody
    public Result addJob(@RequestBody XxlJobInfo xxlJobInfo) {
        log.info("新增xxlJob调度任务入参：{}", JSON.toJSONString(xxlJobInfo));
        Result result = xxlJobTriggerService.addJob(xxlJobInfo);
        log.info("新增xxlJob调度任务出参：{}", JSON.toJSONString(result));
        return result;
    }

    @RequestMapping("/updateJob")
    @ResponseBody
    public Result updateJob(@RequestBody XxlJobInfo xxlJobInfo) {
        log.info("更新xxlJob调度任务入参：{}", JSON.toJSONString(xxlJobInfo));
        Result result = xxlJobTriggerService.updateJob(xxlJobInfo);
        log.info("更新xxlJob调度任务出参：{}", JSON.toJSONString(result));
        return result;
    }

    @RequestMapping("/pauseJob")
    @ResponseBody
    public Result pauseJob(@RequestBody XxlJobInfo xxlJobInfo) {
        log.info("暂停xxlJob调度任务入参：{}", JSON.toJSONString(xxlJobInfo));
        Result result = xxlJobTriggerService.pauseJob(xxlJobInfo);
        log.info("暂停xxlJob调度任务出参：{}", JSON.toJSONString(result));
        return result;
    }
}
