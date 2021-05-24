package com.snk.userconumer.service.impl;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.snk.common.exception.BusinessException;
import com.snk.common.utils.IdWorkerUtil;
import com.snk.userconumer.dao.OrderMapper;
import com.snk.userconumer.feign.amqp.OrderFeignService;
import com.snk.userconumer.pojo.domain.BrokerMessageLog;
import com.snk.userconumer.pojo.domain.Order;
import com.snk.userconumer.pojo.param.OrderParam;
import com.snk.userconumer.service.BrokerMessageLogService;
import com.snk.userconumer.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-14
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    /**远程调用ampq服务接口*/
    @Autowired
    OrderFeignService orderFeignService;
    /**可靠性消息投递记录表*/
    @Autowired
    BrokerMessageLogService brokerMessageLogService;

    /**
     * 保存订单并向mq添加消息
     * @author Cai.ChangJun
     * @param orderDO :
     * @version 1.0.0
     * @Date 2021/5/16 0:55
     */
    @Override
    public boolean save(Order orderDO) {
        IdWorkerUtil worker = new IdWorkerUtil(1,1,1);
        orderDO.setOrderId(worker.nextId());
        boolean isSave = super.save(orderDO);
        if (isSave) {
            BrokerMessageLog brokerMessageLog = new BrokerMessageLog().setMessageId(String.valueOf(orderDO.getOrderId()))
                    .setMessage(JSONUtil.toJsonStr(JSONUtil.parse(orderDO))).setStatus("0").setTryCount(0);
            if (!brokerMessageLogService.save(brokerMessageLog)) {
                throw new BusinessException("消息记录失败回滚当前订单");
            }
            log.info("调用远程消息接口发送{}", JSONUtil.toJsonStr(JSONUtil.parse(orderDO)));
            orderFeignService.orderSend(orderDO);
        }
        return isSave;
    }

    @Override
    public PageInfo<Order> getPageInfo(OrderParam orderParam) {
        PageHelper.startPage(orderParam.getPageNum(),orderParam.getPageSize());
        return new PageInfo<Order>(orderMapper.selectList(null));
    }
}
