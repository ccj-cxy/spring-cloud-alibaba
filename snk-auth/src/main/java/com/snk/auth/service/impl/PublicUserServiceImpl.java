package com.snk.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snk.auth.dao.PublicUserMapper;
import com.snk.auth.pojo.domain.PublicUser;
import com.snk.auth.pojo.dto.OrdinaryEmailDTO;
import com.snk.auth.service.PublicUserService;
import com.snk.common.exception.LoginException;
import com.snk.common.utils.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-26
 */
@Service
@Slf4j
public class PublicUserServiceImpl extends ServiceImpl<PublicUserMapper, PublicUser> implements PublicUserService {
    @Autowired
    private PublicUserMapper publicUserMapper;
    @Autowired
    private SendEmailService sendEmail;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(PublicUser entity) {
        entity.setPassword(Base64Util.encodeToString(entity.getPassword().getBytes()));
        //发送注册成功邮件
        sendEmail.sendEmail(OrdinaryEmailDTO.builder().to(entity.getEmail()).subject("注册成功通知邮件")
                .text("恭喜您成功注册！").build());
        return super.save(entity);
    }


    @Override
    public PublicUser getUserByName(String username) {
        QueryWrapper<PublicUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PublicUser::getName,username);
        //不为空，否则抛出登录异常
        PublicUser publicUser = Optional.ofNullable(publicUserMapper.selectOne(queryWrapper)).orElseThrow(LoginException::new);
        return publicUser;
    }
}
