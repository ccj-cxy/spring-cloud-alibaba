package com.snk.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snk.auth.pojo.domain.PublicResource;
import com.snk.auth.pojo.domain.PublicRoleResource;
import com.snk.auth.pojo.dto.RoleLinkResourceDTO;

import java.util.Set;

/**
 * <p>
 * 角色资源关联表 服务类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-28
 */
public interface PublicRoleResourceService extends IService<PublicRoleResource> {

    /**
     * 根据角色id查询出当前用户包含的角色所拥有的所有资源 最终定位为改用户的所有资源
     * @description:
     * @author Cai.ChangJun
     * @param roleIds: 角色id集合
     * @return Set<PublicResource> : 当前查询roleId下所有的资源信息
     * @version 1.0.0
     * @Date 2021/5/28 14:54
     */
    Set<PublicResource> getAllResource(Set<Integer> roleIds);

    /**
     * 添加角色关联资源
     * @description:
     * @author Cai.ChangJun
     * @param roleLinkResource: 用户关联角色
     * @return:
     * @version 1.0.0
     * @Date 2021/5/28 17:08
     */
    void addRoleResource(RoleLinkResourceDTO roleLinkResource);
}
