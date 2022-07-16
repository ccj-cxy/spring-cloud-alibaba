package com.snk.userconumer.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snk.common.exception.BusinessException;
import com.snk.common.webRetrun.Response;
import com.snk.userconumer.pojo.domain.BrokerMessageLog;
import com.snk.userconumer.service.BrokerMessageLogService;
import org.apache.poi.ss.formula.functions.T;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 可靠性消息投递记录-订单 前端控制器
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-16
 */
@RestController
@RequestMapping("/brokerMessageLog")
public class BrokerMessageLogController {

    @Autowired
    BrokerMessageLogService brokerMessageLogService;
    @Autowired
    private Redisson redisson;

    /**根据消息id修改消息冗余表状态*/
    @PostMapping("/modifyMessageStatus")
    public Response modifyMessageStatus(@RequestBody @Validated BrokerMessageLog brokerMessageLog) throws InterruptedException {
        QueryWrapper<BrokerMessageLog> brokerMessageLogQueryWrapper = new QueryWrapper<>();
        brokerMessageLogQueryWrapper.eq("message_id", brokerMessageLog.getMessageId());
        RLock lock = redisson.getLock(brokerMessageLog.getMessageId());
        lock.lock(10, TimeUnit.SECONDS);
        boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        if (res) {
            try {
                //添加当前对象锁
                BrokerMessageLog brokerMessageLog1 = Optional.ofNullable(brokerMessageLogService.getOne(brokerMessageLogQueryWrapper)).orElseThrow(() -> new BusinessException("未查询到消息"));
                //防止重复消费造成过度修改
                if ("3".equalsIgnoreCase(brokerMessageLog1.getStatus())) {
                    return new Response<>();
                }
                return new Response<>(brokerMessageLogService.update(brokerMessageLog, brokerMessageLogQueryWrapper));
            } finally {
                lock.unlock();
            }
        }
        return new Response<>(500,"失败！");
    }
}

