package com.snk.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snk.auth.pojo.domain.PublicUserRole;
import com.snk.auth.pojo.dto.UserLinkRoleDTO;

import java.util.Set;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-27
 */
public interface PublicUserRoleService extends IService<PublicUserRole> {

    /**
     * <p>
     * 示例：
     * UserLinkRoleDTO userLinkRoleDTO = new UserLinkRoleDTO();
     * PublicUserRoleService pur =  new PublicUserRoleServiceImpl
     * pur.addUserLinkRole(userLinkRoleDTO);
     * </p>
     *
     * @param userLinkRoleDTO : 用户关联角色
     * @description: 添加用户角色绑定关系，在添加前会先清空原先绑定关系，所以一定要传入当前用户所有角色信息
     * @author Cai.ChangJun
     * @return:
     * @version 1.0.0
     * @Date 2021/5/27 23:50
     */
    void addUserLinkRole(UserLinkRoleDTO userLinkRoleDTO);

    /**
     * 根据用户id查询当前用户所拥有的所有角色id
     * @description : 根据用户id查询当前用户所拥有的所有角色id
     * @author Cai.ChangJun
     * @param userId: 用户id
     * @return Set<Integer>: 角色id集合
     * @version 1.0.0
     * @Date 2021/5/28 9:21
     */
    Set<Integer> findRoleIds(Integer userId);

}