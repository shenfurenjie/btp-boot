package com.rj.btp.biz.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 任务Handler示例（Bean模式）
 * e
 * 开发步骤：
 * 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到执行器工厂：在 "JFinalCoreConfig.initXxlJobExecutor" 中手动注册，注解key值对应的是调度中心新建任务的JobHandler属性的值。
 * 3、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 * @author xuxueli 2015-12-19 19:43:36
 */
@Slf4j
@JobHandler(value = "btpJobHandler")
@Component
public class BtpJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        log.info("XXL-JOB, Hello World.");

        for (int i = 0; i < 5; i++) {
            log.info("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        return SUCCESS;
    }

}
