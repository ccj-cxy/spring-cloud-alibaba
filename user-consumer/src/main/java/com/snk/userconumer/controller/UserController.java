package com.snk.userconumer.controller;

import com.snk.common.exception.BusinessException;
import com.snk.userconumer.feign.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * openfeign演化测试类
 * @author Cai.ChangJun
 * @version 1.0.0
 * @Date 2021/5/14 22:49
 */
@RestController
@RequestMapping("user")
@Api(value = "微服务测试接口",tags = "openfeign衍化过程")
public class UserController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /***/
    @GetMapping("consumer")
    @ApiOperation(value = "远程调用",notes = "通过restTemplate根据ip+port发送请求远程调用")
    public String consumer() {
        ServiceInstance provider = loadBalancerClient.choose("user-provider");
        String url = "http://"+provider.getHost()+":"+provider.getPort()+"/user/config";
        RestTemplate restTemp = new RestTemplate();
        String result = restTemp.getForObject(url, String.class);
        return result;
    }


    /**feign*/
    @Autowired
    private UserService userService;
    @GetMapping("consumer-feign")
    @ApiOperation(value = "远程调用",notes = "通过feign发送请求远程调用")
    public String userService() {
        String result = userService.config();
        return result;
    }

    /**Ribbon*/
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("consumer-ribbon")
    @ApiOperation(value = "远程调用",notes = "通过ribbon发送请求远程调用")
    public String consumerribbon() {
        String url = "http://user-provider/user/config";
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }


    @GetMapping("error")
    public String error() {
        throw new BusinessException("访问接口异常");
    }


}