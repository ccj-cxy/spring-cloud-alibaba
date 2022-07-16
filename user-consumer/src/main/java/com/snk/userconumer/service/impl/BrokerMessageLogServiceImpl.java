package com.snk.userconumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snk.redis.utils.RedisUtil;
import com.snk.userconumer.dao.BrokerMessageLogMapper;
import com.snk.userconumer.pojo.domain.BrokerMessageLog;
import com.snk.userconumer.service.BrokerMessageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean update(BrokerMessageLog entity, Wrapper<BrokerMessageLog> updateWrapper) {
        //添加锁并设置10秒过期
        redisUtil.setEx(entity.getMessageId(),entity.getMessageId(),10, TimeUnit.SECONDS);
        try {
            boolean update = super.update(entity, updateWrapper);
            redisUtil.delete(entity.getMessageId());
            return update;
        }catch (Exception e){
            redisUtil.delete(entity.getMessageId());
        }
        return false;
    }
}
