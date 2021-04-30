/**
 * Copyright  HuaTu
 * 安徽华图信息科技有限公司
 * http://www.myhuatu.com/
 * All rights reserved.
 */
package com.snk.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义的全局过滤器，作用是统一鉴权
 * 要求：必须实现GlobalFilter, Ordered ，并且实现里面的两个方法
 * @author :Cai.ChangJun
 * @Description : 权鉴全局过滤器
 * @Date : 2021/1/6
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    //过滤器逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //统一鉴权逻辑编写
        String token = exchange.getRequest().getHeaders().getFirst("token");
        //TODO 执行鉴权逻辑
        /*if (!StringUtils.equals("admin",token)){
           //认证失败
            log.error("认证失败" );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }*/
        //直接放行
        return chain.filter(exchange);
    }

    //标识当前过滤器的优先级，返回值越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
