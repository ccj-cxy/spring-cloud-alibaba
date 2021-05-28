package com.snk.auth.dao;

import com.snk.auth.pojo.domain.PublicRoleResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色资源关联表 Mapper 接口
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-28
 */
@Mapper
public interface PublicRoleResourceMapper extends BaseMapper<PublicRoleResource> {

}
