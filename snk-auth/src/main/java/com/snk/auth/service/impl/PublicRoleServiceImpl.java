package com.snk.auth.service.impl;

import com.snk.auth.pojo.domain.PublicRole;
import com.snk.auth.dao.PublicRoleMapper;
import com.snk.auth.service.PublicRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-27
 */
@Service
public class PublicRoleServiceImpl extends ServiceImpl<PublicRoleMapper, PublicRole> implements PublicRoleService {

}
