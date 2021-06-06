package com.snk.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snk.auth.pojo.domain.PublicOrg;
import com.snk.auth.pojo.dto.OrgTreeDTO;

import java.util.List;

/**
 * <p>
 * 部门机构  服务类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-06-06
 */
public interface PublicOrgService extends IService<PublicOrg> {

    /**
     * 递归查询组织树
     * @author Cai.ChangJun
     * @return: 组织树
     * @version 1.0.0
     * @Date 2021/6/2 20:18
     */
    List<OrgTreeDTO> getOrgTree(Integer orgId);
}
