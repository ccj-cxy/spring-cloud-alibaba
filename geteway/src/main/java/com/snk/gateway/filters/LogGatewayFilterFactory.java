package com.snk.gateway.filters;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author :Cai.ChangJun
 * @Description : 自定义局部过滤器
 * @Date : 2021/1/6
 */
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {
    //构造函数
    public LogGatewayFilterFactory() {
        super(Config.class);
    }

    //读取配置文件中的参数，赋值到配置类中
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog","cacheLog");
    }



    //过滤器逻辑
    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if (config.isCacheLog()){
                    System.out.println("开启缓存日志"+exchange.getRequest().getQueryParams());
                }
                if (config.isConsoleLog()){
                    System.out.println("开启打印日志");
                }
                return chain.filter(exchange);
            }
        };
    }

    //配置类，接收配置参数
    @Data
    @NoArgsConstructor
    public static class Config{
        private boolean consoleLog;
        private boolean cacheLog;
    }
}
