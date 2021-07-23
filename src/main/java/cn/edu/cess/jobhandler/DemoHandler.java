package cn.edu.cess.jobhandler;

import cn.edu.cess.util.StringUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;

/**
 * @Package: cn.edu.cess.jobhandler
 * @Description:每分钟执行一次
 * @Author: LuDeSong
 * @Date: 2021-7-23 11:26
 */
@JobHandler("DemoHandler")
@Slf4j
public class DemoHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        log.info("XXL-JOB, 账户清零调度开始执行");
        XxlJobLogger.log("XXL-JOB, 账户清零调度开始执行");
        long startTime = System.currentTimeMillis();

        if (StringUtil.isEmpty(param)) {
            log.error("XXL-JOB, 账户清零调度参数为空");
            return FAIL;
        }
        executeHandler(param);

        log.info("XXL-JOB, 账户清零调度结束,耗时:" + (System.currentTimeMillis() - startTime));
        XxlJobLogger.log("XXL-JOB, 账户清零调度结束,耗时:" + (System.currentTimeMillis() - startTime));
        return SUCCESS;
    }

    private void executeHandler(String param) {
        log.info("XXL-JOB, 入参：{}", param);
    }
}

