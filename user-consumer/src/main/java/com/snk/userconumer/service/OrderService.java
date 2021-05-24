package com.snk.userconumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.snk.userconumer.pojo.domain.Order;
import com.snk.userconumer.pojo.param.OrderParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-14
 */
public interface OrderService extends IService<Order> {

    /**
     * 分页查询订单
     * @author Cai.ChangJun
     * @param orderParam : 查询订单变量
     * @version 1.0.0
     * @Date 2021/5/19 20:16
     */
    PageInfo<Order> getPageInfo(OrderParam orderParam);
}
