package com.snk.xxljob.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : xxl任务添加模型
 * @Date : 2021/10/29
 */
@Data
@Accessors(chain = true)
public class JobInfoDTO {
    private Integer id;

    private Integer jobGroup;

    private String jobCron;

    private String scheduleConf;

    private String cronGen_display;

    private String jobDesc;

    private String author;

    private String alarmEmail;

    private String scheduleType = "CRON";

    private String executorRouteStrategy = "FIRST";

    private String misfireStrategy = "DO_NOTHING";

    private String executorHandler;

    private String executorParam;

    private String executorBlockStrategy = "SERIAL_EXECUTION";

    private Integer executorTimeout = 0;

    private Integer executorFailRetryCount = 0;

    private String glueType = "BEAN";

    private String glueRemark = "GLUE代码初始化";

    private Integer triggerStatus = 0;

    private Integer triggerLastTime = 0;

    private Integer triggerNextTime = 0;

}
