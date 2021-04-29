package com.snk.userconumer.controller;

import com.snk.userconumer.feign.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /***/
    @RequestMapping("consumer")
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
    @RequestMapping("consumer-feign")
    public String userService() {
        String result = userService.config();
        return result;
    }

    /**Ribbon*/
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("consumer-ribbon")
    public String consumerribbon() {
        String url = "http://user-provider/user/config";
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }


}