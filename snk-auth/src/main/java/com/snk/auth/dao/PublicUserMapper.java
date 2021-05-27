package com.snk.auth.dao;

import com.snk.auth.pojo.domain.PublicUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-26
 */
@Mapper
public interface PublicUserMapper extends BaseMapper<PublicUser> {

}
