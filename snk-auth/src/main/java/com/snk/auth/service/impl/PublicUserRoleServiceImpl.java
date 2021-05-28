package com.snk.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snk.auth.dao.PublicUserRoleMapper;
import com.snk.auth.pojo.domain.PublicUserRole;
import com.snk.auth.pojo.dto.UserLinkRoleDTO;
import com.snk.auth.service.PublicUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-27
 */
@Service
public class PublicUserRoleServiceImpl extends ServiceImpl<PublicUserRoleMapper, PublicUserRole> implements PublicUserRoleService {

    @Autowired
    private PublicUserRoleMapper publicUserRoleMapper;

    @Override
    public void addUserLinkRole(UserLinkRoleDTO userLinkRoleDTO) {
        QueryWrapper<PublicUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PublicUserRole::getUserId,userLinkRoleDTO.getUserId());
        //清空当前用户下所有角色信息
        publicUserRoleMapper.delete(queryWrapper);
        //重新绑定用户角色信息
        List<PublicUserRole> list = new ArrayList<>();
        userLinkRoleDTO.getRoleIds().forEach(roleId->{
            PublicUserRole publicUserRole = new PublicUserRole();
            publicUserRole.setUserId(userLinkRoleDTO.getUserId());
            publicUserRole.setRoleId(roleId);
            list.add(publicUserRole);
        });
        this.saveBatch(list);
    }

    @Override
    public Set<Integer> findRoleIds(Integer userId) {
        QueryWrapper<PublicUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PublicUserRole::getUserId,userId);
        List<PublicUserRole> publicUserRoles = publicUserRoleMapper.selectList(queryWrapper);
        return publicUserRoles.stream().map(PublicUserRole::getRoleId).collect(Collectors.toSet());
    }
}
