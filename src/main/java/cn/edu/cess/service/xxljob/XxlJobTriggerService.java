package cn.edu.cess.service.xxljob;

import cn.edu.cess.entity.xxljob.XxlJobInfo;
import cn.edu.cess.mapper.xxljob.XxlJobDao;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.util.ConfigUtil;
import cn.edu.cess.util.HttpSendUtil;
import cn.edu.cess.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.jst.stat.service.xxljob
 * @Description:
 * @Author: LuDeSong
 * @Date: 2021-1-5 14:32
 */
@Service
public class XxlJobTriggerService {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    XxlJobDao xxlJobDao;

    /**
     * @param xxlJobInfo 5个必须字段：executorHandler,executorParam,jobCron,jobDesc,author
     * @return
     */
    public Result addJob(XxlJobInfo xxlJobInfo) {
        log.info("新增xxlJob调度任务入参：{}", JSON.toJSONString(xxlJobInfo));
        if (StringUtil.isEmpty(xxlJobInfo.getExecutorHandler())) {
            return ResultFactory.buildFailResult("executorHandler不能为空");
        }
        if (StringUtil.isEmpty(xxlJobInfo.getExecutorParam())) {
            return ResultFactory.buildFailResult("executorParam不能为空");
        }
        if (StringUtil.isEmpty(xxlJobInfo.getJobCron())) {
            return ResultFactory.buildFailResult("jobCron不能为空");
        }
        if (StringUtil.isEmpty(xxlJobInfo.getJobDesc())) {
            return ResultFactory.buildFailResult("jobDesc不能为空");
        }
        if (StringUtil.isEmpty(xxlJobInfo.getAuthor())) {
            return ResultFactory.buildFailResult("author不能为空");
        }

        List<XxlJobInfo> xxlJobInfos = xxlJobDao.triggerList(xxlJobInfo.getExecutorHandler());
        if (xxlJobInfos == null || xxlJobInfos.size() <= 0) {
            log.error("任务调度未初始化");
            return ResultFactory.buildFailResult("任务调度未初始化");
        }
        XxlJobInfo jobInfo = xxlJobInfos.get(0);
        xxlJobInfo.setJobGroup(jobInfo.getJobGroup());
        try {
            String resCode = addXxlJobTrigger(xxlJobInfo);
            if (!"200".equals(resCode)) {
                log.error("请求新增xxlJob调度任务失败");
                return ResultFactory.buildFailResult("请求新增xxlJob调度任务失败");
            }
        } catch (Exception e) {
            log.info("任务调度新增执行异常:{}" + e.getMessage(), e);
            return ResultFactory.buildFailResult("任务调度新增执行异常");
        }
        return ResultFactory.buildSuccessResult();
    }

    /**
     * @param xxlJobInfo 5个必须字段：executorHandler,executorParam,jobCron,jobDesc,author
     * @return
     */
    public Result updateJob(XxlJobInfo xxlJobInfo) {
        log.info("更新xxlJob调度任务入参：{}", JSON.toJSONString(xxlJobInfo));
        if (StringUtil.isEmpty(xxlJobInfo.getExecutorHandler())) {
            return ResultFactory.buildFailResult("executorHandler不能为空");
        }
        if (StringUtil.isEmpty(xxlJobInfo.getExecutorParam())) {
            return ResultFactory.buildFailResult("executorParam不能为空");
        }
        if (StringUtil.isEmpty(xxlJobInfo.getJobCron())) {
            return ResultFactory.buildFailResult("jobCron不能为空");
        }
        if (StringUtil.isEmpty(xxlJobInfo.getJobDesc())) {
            return ResultFactory.buildFailResult("jobDesc不能为空");
        }
        if (StringUtil.isEmpty(xxlJobInfo.getAuthor())) {
            return ResultFactory.buildFailResult("author不能为空");
        }

        XxlJobInfo model = new XxlJobInfo();
        model.setExecutorHandler(xxlJobInfo.getExecutorHandler());
        model.setExecutorParam(xxlJobInfo.getExecutorParam());
        List<XxlJobInfo> xxlJobInfos = xxlJobDao.triggerListByModel(model);
        if (xxlJobInfos == null || xxlJobInfos.size() <= 0) {
            log.error("任务调度不存在");

            return ResultFactory.buildFailResult("任务调度不存在");

        }
        XxlJobInfo jobInfo = xxlJobInfos.get(0);
        xxlJobInfo.setId(jobInfo.getId());
        xxlJobInfo.setJobGroup(jobInfo.getJobGroup());

        try {
            String resCode = updateXxlJobTrigger(xxlJobInfo);
            if (!"200".equals(resCode)) {
                log.error("请求更新xxlJob调度任务失败");
                return ResultFactory.buildFailResult("请求更新xxlJob调度任务失败");
            }
        } catch (Exception e) {
            log.info("任务调度更新执行异常:{}" + e.getMessage(), e);
            return ResultFactory.buildFailResult("任务调度更新执行异常");
        }
        return ResultFactory.buildSuccessResult();

    }

    /**
     * @param xxlJobInfo 2个必须字段：executorHandler,executorParam
     * @return
     */
    public Result pauseJob(XxlJobInfo xxlJobInfo) {
        log.info("暂停xxlJob调度任务入参：{}", JSON.toJSONString(xxlJobInfo));
        if (StringUtil.isEmpty(xxlJobInfo.getExecutorHandler())) {
            return ResultFactory.buildFailResult("executorHandler不能为空");
        }
        if (StringUtil.isEmpty(xxlJobInfo.getExecutorParam())) {
            return ResultFactory.buildFailResult("executorParam不能为空");
        }

        XxlJobInfo model = new XxlJobInfo();
        model.setExecutorHandler(xxlJobInfo.getExecutorHandler());
        model.setExecutorParam(xxlJobInfo.getExecutorParam());
        List<XxlJobInfo> xxlJobInfos = xxlJobDao.triggerListByModel(model);
        if (xxlJobInfos == null || xxlJobInfos.size() <= 0) {
            log.error("任务调度不存在");
            return ResultFactory.buildFailResult("任务调度不存在");
        }
        XxlJobInfo jobInfo = xxlJobInfos.get(0);

        try {
            String resCode = pauseXxlJobTrigger(jobInfo);
            if (!"200".equals(resCode)) {
                log.error("请求暂停xxlJob调度任务失败");
                return ResultFactory.buildFailResult("请求暂停xxlJob调度任务失败");
            }
        } catch (Exception e) {
            log.info("任务调度暂停执行异常:{}" + e.getMessage(), e);

            return ResultFactory.buildFailResult("任务调度暂停执行异常");
        }
        return ResultFactory.buildSuccessResult();

    }

    public String addXxlJobTrigger(XxlJobInfo xxlJobInfo) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("jobGroup", xxlJobInfo.getJobGroup() + "");
        paramMap.put("jobDesc", xxlJobInfo.getJobDesc());
        paramMap.put("jobCron", xxlJobInfo.getJobCron());
        paramMap.put("executorHandler", xxlJobInfo.getExecutorHandler());
        paramMap.put("executorParam", xxlJobInfo.getExecutorParam());
        paramMap.put("author", xxlJobInfo.getAuthor());
        paramMap.put("executorRouteStrategy", "FIRST");
        paramMap.put("glueType", "BEAN");
        paramMap.put("executorBlockStrategy", "SERIAL_EXECUTION");
        paramMap.put("executorFailStrategy", "FAIL_ALARM");
        paramMap.put("glueRemark", "GLUE代码初始化");

        String strResponse = HttpSendUtil.sendPost(ConfigUtil.getProperty("xxl.jobinfo.add.url"), paramMap);
        JSONObject resultObj = JSONObject.parseObject(strResponse);
        log.info("请求xxlJob新增任务接口返回：{}", JSON.toJSONString(strResponse));
        return resultObj.getString("code");
    }

    public String updateXxlJobTrigger(XxlJobInfo xxlJobInfo) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("id", xxlJobInfo.getId() + "");

        paramMap.put("jobGroup", xxlJobInfo.getJobGroup() + "");
        paramMap.put("jobDesc", xxlJobInfo.getJobDesc());
        paramMap.put("jobCron", xxlJobInfo.getJobCron());
        paramMap.put("executorHandler", xxlJobInfo.getExecutorHandler());
        paramMap.put("executorParam", xxlJobInfo.getExecutorParam());
        paramMap.put("author", xxlJobInfo.getAuthor());
        paramMap.put("executorRouteStrategy", "FIRST");
        paramMap.put("glueType", "BEAN");
        paramMap.put("executorBlockStrategy", "SERIAL_EXECUTION");
        paramMap.put("executorFailStrategy", "FAIL_ALARM");
        paramMap.put("glueRemark", "GLUE代码初始化");

        String strResponse = HttpSendUtil.sendPost(ConfigUtil.getProperty("xxl.jobinfo.update.url"), paramMap);
        JSONObject resultObj = JSONObject.parseObject(strResponse);
        log.info("请求xxlJob更新任务接口返回：{}", JSON.toJSONString(strResponse));
        return resultObj.getString("code");
    }

    public String pauseXxlJobTrigger(XxlJobInfo xxlJobInfo) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("id", xxlJobInfo.getId() + "");

        String strResponse = HttpSendUtil.sendPost(ConfigUtil.getProperty("xxl.jobinfo.pause.url"), paramMap);
        JSONObject resultObj = JSONObject.parseObject(strResponse);
        log.info("请求xxlJob暂停任务接口返回：{}", JSON.toJSONString(strResponse));
        return resultObj.getString("code");
    }
}
