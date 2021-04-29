package com.snk.userconumer.feign;

import org.springframework.stereotype.Service;

@Service
public class UserServiceFallback implements UserService {
    @Override
    public String config() {
        return "user-fallback";
    }
}