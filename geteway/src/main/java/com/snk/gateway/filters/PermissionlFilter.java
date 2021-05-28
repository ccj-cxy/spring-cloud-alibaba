package com.snk.gateway.filters;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.alibaba.nacos.client.utils.JSONUtils;
import com.snk.gateway.config.IgnoreWhiteProperties;
import com.snk.gateway.pojo.Response;
import com.snk.gateway.pojo.dto.ResourceDTO;
import com.snk.gateway.pojo.dto.UserDTO;
import com.snk.gateway.utils.StringUtil;
import com.snk.jwt.constants.TokenConstant;
import com.snk.redis.utils.RedisUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义的全局过滤器，作用是统一鉴权
 * 要求：必须实现GlobalFilter, Ordered ，并且实现里面的两个方法
 * @author :Cai.ChangJun
 * @Description : 权鉴全局过滤器
 * @Date : 2021/1/6
 */
@Slf4j
@Component
public class PermissionlFilter implements GlobalFilter, Ordered {
    /**注入redis工具类操作缓存实现登录认证*/
    @Autowired
    RedisUtil redisUtil;
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
            String user = redisUtil.get(cacheKey);
            if (StringUtil.isNotEmpty(user)) {
                //复杂json处理
                TypeReference <UserDTO> list =new TypeReference <UserDTO>(){};
                Type t = list.getType();
                UserDTO userDTO =  JSONUtil.toBean(user,t,false);
                //获取用户所拥有的资源对比
                Set<ResourceDTO> resources = userDTO.getResources();
                Set<String> permissions = resources.stream().map(ResourceDTO::getPermissionName).collect(Collectors.toSet());
                //系统管理员可以绕过系统权鉴
                if (!permissions.contains(url) && (userDTO.getType() != 0)) {
                    response.setStatusCode(HttpStatus.FORBIDDEN);
                    return authenticationError(response,"无权限访问改接口",HttpStatus.FORBIDDEN.value());
                }
                ServerHttpRequest request = exchange.getRequest().mutate().header("user", user).build();
                exchange = exchange.mutate().request(request).build();
            }else {
                log.error("未登录或登录过期");
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return authenticationError(response,"未登录或登录过期",HttpStatus.UNAUTHORIZED.value());
            }

        //直接放行
        return chain.filter(exchange);
    }

    @SneakyThrows
    public Mono<Void> authenticationError(ServerHttpResponse response, String tip,int httpStatus) {
        Response<Object> objectResponse = new Response<>(httpStatus, tip);
        String json = JSONUtils.serializeObject(objectResponse);
        byte[] bits = json.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.getHeaders().add("Content-Type", "text/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    //标识当前过滤器的优先级，返回值越小优先级越高
    @Override
    public int getOrder() {
        return -100;
    }
}
