package com.snk.userconumer.controller;


import com.github.pagehelper.PageInfo;
import com.snk.userconumer.pojo.domain.Order;
import com.snk.userconumer.pojo.param.OrderParam;
import com.snk.userconumer.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-14
 */
@RestController
@RequestMapping("/order")
@Api(value = "订单服务" ,tags = "订单服务")
public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping("/save")
    @ApiOperation(value = "添加订单",notes = "插入订单并发送消息至mq服务")
    public Order saveOrder(@RequestBody Order orderDO) {
        orderService.save(orderDO);
        return orderDO;
    }

    @GetMapping("/getPage")
    @ApiOperation(value = "获取所有订单",notes = "获取已经消费的订单")
    public PageInfo<Order> saveOrder(@RequestBody @Validated OrderParam orderDO) {
        return orderService.getPageInfo(orderDO);
    }
}

