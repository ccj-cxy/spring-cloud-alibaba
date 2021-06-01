package com.snk.auth.service.impl;

import cn.hutool.json.JSONUtil;
import com.snk.auth.pojo.domain.PublicUser;
import com.snk.auth.pojo.dto.UserDTO;
import com.snk.auth.pojo.enums.LoginType;
import com.snk.auth.service.LoginService;
import com.snk.auth.service.PublicRoleResourceService;
import com.snk.auth.service.PublicUserRoleService;
import com.snk.auth.service.PublicUserService;
import com.snk.common.exception.LoginException;
import com.snk.common.utils.Base64Util;
import com.snk.jwt.constants.TokenConstant;
import com.snk.jwt.utils.JWTUtil;
import com.snk.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : snk系统登录策略
 * @Date : 2021/6/1
 */
@Component
public class SnkLoginStrategy implements LoginService {
    @Autowired
    private PublicUserService publicUserService;

    @Autowired
    private PublicUserRoleService publicUserRoleService;
    @Autowired
    private PublicRoleResourceService publicRoleResourceService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public LoginType getLoginType() {
        return LoginType.SNK;
    }

    @Override
    public UserDTO login(String username, String password) {
        Assert.notNull(password,"用户密码不能为空");
        PublicUser publicUser = publicUserService.getUserByName(username);
        String userPassword = Base64Util.base64Decoder(publicUser.getPassword());
        //判断密码是否正确
        if (password.equals(userPassword)) {
            //密码正确，生成token
            //将密码置空token中不携带密码信息
            publicUser.setPassword(null);
            String jsonStr = JSONUtil.toJsonStr(JSONUtil.parse(publicUser));
            //过期时间不设使用默认时间，为一个小时
            String token = jwtUtil.createJWT(UUID.randomUUID().toString(),jsonStr , null);
            //同步对象转换为UserDTO
            UserDTO userDTO = JSONUtil.toBean(jsonStr, UserDTO.class);
            //得到用户角色添加到返回值
            Set<Integer> roleIds = publicUserRoleService.findRoleIds(userDTO.getId());
            userDTO.setRoleIds(roleIds);
            //得到用户所有资源添加到返回值
            userDTO.setResources(publicRoleResourceService.getAllResource(roleIds));
            userDTO.setToken(token);
            String cacheKey = TokenConstant.CACHE_TOKEN_KEY+token;
            //设置缓存并加上30分钟默认时间
            redisUtil.set(cacheKey,JSONUtil.toJsonStr(JSONUtil.parse(userDTO)));
            redisUtil.expire(cacheKey,30L, TimeUnit.MINUTES);
            //将登录对象返回
            return userDTO;
        }else {
            //密码不正确，抛出登录异常
            throw new LoginException(String.valueOf(publicUser.getId()));
        }
    }
}
