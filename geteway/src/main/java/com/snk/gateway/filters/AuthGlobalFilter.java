package com.snk.gateway.filters;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.alibaba.nacos.client.utils.JSONUtils;
import com.snk.gateway.config.IgnoreWhiteProperties;
import com.snk.gateway.pojo.Response;
import com.snk.gateway.pojo.dto.UserDTO;
import com.snk.gateway.utils.StringUtil;
import com.snk.jwt.constants.TokenConstant;
import com.snk.jwt.utils.JWTUtil;
import com.snk.redis.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

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
    /**注入redis工具类操作缓存实现登录认证*/
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    JWTUtil jwtUtil;
    /**排除过滤的 uri 地址*/
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;
    /**过滤器逻辑*/
    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        // 跳过不需要验证的路径
        if (StringUtil.matches(url, ignoreWhite.getWhites()))
        {
            return chain.filter(exchange);
        }
        //统一鉴权逻辑编写
        String token = exchange.getRequest().getHeaders().getFirst("token");
        //缓存key
        String cacheKey = TokenConstant.CACHE_TOKEN_KEY+token;
        ServerHttpResponse response = exchange.getResponse();
        // 执行鉴权逻辑
        if (StringUtils.isEmpty(token)){
            return authenticationError(response,"鉴权失败，无token或类型");
        }
        try {
            //从token中解析用户信息并设置到Header中去
            Claims claims = jwtUtil.parseJWT(token);
            String userStr = claims.getSubject();
            log.info("AuthGlobalFilter.filter() user:{}", userStr);
            ServerHttpRequest request = exchange.getRequest().mutate().header("user", userStr).build();
            exchange = exchange.mutate().request(request).build();
            //确认下缓存，未过期 续时 过期重新缓存
            if (StringUtil.isEmpty(redisUtil.get(cacheKey))) {
                //过期 重新设置缓存
                redisUtil.set(cacheKey,userStr);
            }
            redisUtil.expire(cacheKey,30,TimeUnit.MINUTES);
        } catch (Exception e) {
            String user = redisUtil.get(cacheKey);
            if (StringUtil.isNotEmpty(user)) {
                //继续设置有效期，直到长时间不操作删除key后提示用户登录时间过长重新登录生成新的token
                redisUtil.expire(cacheKey,30, TimeUnit.MINUTES);
                //做一下日志记录
                //复杂json处理
                TypeReference<UserDTO> list =new TypeReference <UserDTO>(){};
                Type t = list.getType();
                UserDTO userDTO =  JSONUtil.toBean(user,t,false);
                log.info("AuthGlobalFilter.filter() user:{}", userDTO);
                ServerHttpRequest request = exchange.getRequest().mutate().header("user", user).build();
                exchange = exchange.mutate().request(request).build();
            }else {
                log.error("未登录或登录过期");
                return authenticationError(response,"未登录或登录过期");
            }
        }
        //直接放行
        return chain.filter(exchange);
    }

    @SneakyThrows
    public Mono<Void> authenticationError(ServerHttpResponse response, String tip) {
        Response<Object> objectResponse = new Response<>(HttpStatus.UNAUTHORIZED.value(), tip);
        String json = JSONUtils.serializeObject(objectResponse);
        byte[] bits = json.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "text/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    //标识当前过滤器的优先级，返回值越小优先级越高
    @Override
    public int getOrder() {
        return -200;
    }
}
