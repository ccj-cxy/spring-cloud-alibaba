package com.snk.userconumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user-provider",fallback = UserServiceFallback.class)
public interface UserService {
    @RequestMapping("/user/config")
    String config();
}