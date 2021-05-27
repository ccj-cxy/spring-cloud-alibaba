package com.snk.auth.service.impl;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snk.auth.pojo.domain.PublicUser;
import com.snk.auth.dao.PublicUserMapper;
import com.snk.auth.pojo.dto.UserDTO;
import com.snk.auth.service.PublicUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snk.common.exception.LoginException;
import com.snk.common.utils.Base64Util;
import com.snk.jwt.constants.TokenConstant;
import com.snk.jwt.utils.JWTUtil;
import com.snk.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-26
 */
@Service
public class PublicUserServiceImpl extends ServiceImpl<PublicUserMapper, PublicUser> implements PublicUserService {
    @Autowired
    private PublicUserMapper publicUserMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public boolean save(PublicUser entity) {
        entity.setPassword(Base64Util.encodeToString(entity.getPassword().getBytes()));
        //TODO 获取用户邮箱发送邮件 在未实现 可以直接调用邮件服务
        return super.save(entity);
    }


    @Override
    public UserDTO login(String username, String password) {
        QueryWrapper<PublicUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PublicUser::getName,username);
        //不为空，否则抛出登录异常
        PublicUser publicUser = Optional.ofNullable(publicUserMapper.selectOne(queryWrapper)).orElseThrow(LoginException::new);
        String userPassword = Base64Util.base64Decoder(publicUser.getPassword());
        //判断密码是否正确
        if (password.equals(userPassword)) {
            //密码正确，生成token
            Integer id = publicUser.getId();
            //过期时间不设使用默认时间，为一个小时
            String jsonStr = JSONUtil.toJsonStr(JSONUtil.parse(publicUser));
            String token = jwtUtil.createJWT(UUID.randomUUID().toString(),jsonStr , null);
            //同步对象转换为UserDTO
            UserDTO userDTO = JSONUtil.toBean(jsonStr, UserDTO.class);
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
