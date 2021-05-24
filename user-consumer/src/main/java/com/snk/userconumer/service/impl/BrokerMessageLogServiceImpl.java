package com.snk.userconumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.snk.userconumer.dao.BrokerMessageLogMapper;
import com.snk.userconumer.pojo.domain.BrokerMessageLog;
import com.snk.userconumer.service.BrokerMessageLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 可靠性消息投递记录-订单 服务实现类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-16
 */
@Service
public class BrokerMessageLogServiceImpl extends ServiceImpl<BrokerMessageLogMapper, BrokerMessageLog> implements BrokerMessageLogService {

}
