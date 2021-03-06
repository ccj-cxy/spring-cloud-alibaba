package com.snk.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snk.auth.pojo.domain.PublicResource;
import com.snk.auth.pojo.dto.ResourceTreeDTO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-28
 */
public interface PublicResourceService extends IService<PublicResource> {

    /**
     * 根据资源id查询资源
     * @description:
     * @author Cai.ChangJun
     * @param resourceIds: 资源id集合
     * @return: Set<PublicResource>
     * @version 1.0.0
     * @Date 2021/5/28 15:17
     */
    Set<PublicResource> getResources(Set<Integer> resourceIds);

    /**
     * 递归查询资源树
     * @author Cai.ChangJun
     * @return: 资源树
     * @version 1.0.0
     * @Date 2021/6/2 20:18
     */
    List<ResourceTreeDTO> getResourceTree();
}
