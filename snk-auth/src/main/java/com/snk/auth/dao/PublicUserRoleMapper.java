package com.snk.auth.dao;

import com.snk.auth.pojo.domain.PublicUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-27
 */
@Mapper
public interface PublicUserRoleMapper extends BaseMapper<PublicUserRole> {

}
