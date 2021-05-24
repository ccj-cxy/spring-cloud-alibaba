package com.snk.userconumer.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snk.userconumer.pojo.domain.BrokerMessageLog;
import com.snk.userconumer.service.BrokerMessageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    /**根据消息id修改消息冗余表状态*/
    @PostMapping("/modifyMessageStatus")
    public Boolean modifyMessageStatus(@RequestBody BrokerMessageLog brokerMessageLog) {
        QueryWrapper<BrokerMessageLog> brokerMessageLogQueryWrapper = new QueryWrapper<>();
        brokerMessageLogQueryWrapper.eq("message_id",brokerMessageLog.getMessageId());
        BrokerMessageLog one = brokerMessageLogService.getOne(brokerMessageLogQueryWrapper);
        if ("3".equalsIgnoreCase(one.getStatus())) {
            return true;
        }
        return brokerMessageLogService.update(brokerMessageLog,brokerMessageLogQueryWrapper);
    }


}

