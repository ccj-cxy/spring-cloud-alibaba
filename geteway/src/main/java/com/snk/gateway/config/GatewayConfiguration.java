package com.snk.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :Cai.ChangJun
 * @Description :
 * @Date : 2021/1/7
 */
@Configuration
public class GatewayConfiguration {
    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfiguration(List<ViewResolver> viewResolvers, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolvers;
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    //初始化一个限流的过滤器
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    //配置初始化的限流参数
    @PostConstruct
    public void initGatewayRules(){
        /*Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("product_route")
                .setCount(1) //限流阈值
                .setIntervalSec(1)); //统计时间窗口，单位是秒，默认是一秒
        GatewayRuleManager.loadRules(rules);*/
      /*  Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("user-consumer")
                .setCount(1) //限流阈值
                .setIntervalSec(1)); //统计时间窗口，单位是秒，默认是一秒
        GatewayRuleManager.loadRules(rules);*/

    }
    //配置限流的异常处理器
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers,serverCodecConfigurer);
    }

    //自定义限流异常页面
    @PostConstruct
    public void initBlockExceptionHandler() {
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler(){
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                Map map = new HashMap<>();
                map.put("code",0);
                map.put("message","接口被限流了");
                return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(map));
            }
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }
    @PostConstruct
    private void initCustomizedAps() {
        /*Set<ApiDefinition> definitions = new HashSet<>();
        ApiDefinition api1 = new ApiDefinition("user-consumer")
                .setPredicateItems(new HashSet<ApiPredicateItem>(){
                    {
                        //以/product_serv/product/api1开头的请求
                        add(new ApiPathPredicateItem().setPattern("/user-consumer/**")
                        .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                    }
                });
        *//*ApiDefinition api2 = new ApiDefinition("product_api2")
                .setPredicateItems(new HashSet<ApiPredicateItem>(){
                    {
                        //以/product_serv/product/api2/demo1完整的url路径匹配
                        add(new ApiPathPredicateItem().setPattern("/user-consumer")
                                .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                    }
                });*//*

        definitions.add(api1);
//        definitions.add(api2);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);*/
    }
}
