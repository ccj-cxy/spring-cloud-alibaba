package com.snk.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snk.auth.dao.PublicResourceMapper;
import com.snk.auth.pojo.domain.PublicResource;
import com.snk.auth.pojo.dto.ResourceTreeDTO;
import com.snk.auth.service.PublicResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-28
 */
@Service
public class PublicResourceServiceImpl extends ServiceImpl<PublicResourceMapper, PublicResource> implements PublicResourceService {

    @Autowired
    private PublicResourceMapper publicResourceMapper;

    private volatile List<ResourceTreeDTO> resourceTreeDTOS1 = new ArrayList<>();

    @Override
    public Set<PublicResource> getResources(Set<Integer> resourceIds) {
        QueryWrapper<PublicResource> publicResourceQueryWrapper = new QueryWrapper<>();
        publicResourceQueryWrapper.lambda().in(PublicResource::getId,resourceIds);
        List<PublicResource> publicResources = publicResourceMapper.selectList(publicResourceQueryWrapper);
        return publicResources.stream().collect(Collectors.toSet());
    }

    @Override
    public List<ResourceTreeDTO> getResourceTree() {
        //查询出所有节点避免递归查询数据库拖垮数据库
        List<PublicResource> publicResources = publicResourceMapper.selectList(null);
        publicResources.forEach(resource1 -> {
            ResourceTreeDTO dto1 = new ResourceTreeDTO();
            BeanUtil.copyProperties(resource1,dto1);
            resourceTreeDTOS1.add(dto1);
        });
        List<ResourceTreeDTO> dtos = buildTree();
        resourceTreeDTOS1.clear();
        return dtos;
    }

    /**
     * 构建树结构
     * @author Cai.ChangJun
     * @return: 整个树
     * @version 1.0.0
     * @Date 2021/6/2 23:55
     */
    public List<ResourceTreeDTO> buildTree(){
        List<ResourceTreeDTO> treeMenus =new  ArrayList<>();
        List<ResourceTreeDTO> rootNode = getRootNode();
        for(ResourceTreeDTO menuNode : rootNode) {
            menuNode=buildChildTree(menuNode);
            treeMenus.add(menuNode);
        }
        return treeMenus;
    }

    /**
     * 递归建立子树
     * @author Cai.ChangJun
     * @param pNode: 父节点
     * @return:
     * @version 1.0.0
     * @Date 2021/6/2 23:55
     */
    private ResourceTreeDTO buildChildTree(ResourceTreeDTO pNode){
        List<ResourceTreeDTO> childMenus =new  ArrayList<>();
        for(ResourceTreeDTO menuNode : resourceTreeDTOS1) {
            if(menuNode.getParentId().equals(pNode.getId())) {
                childMenus.add(buildChildTree(menuNode));
            }
        }
        pNode.setChild(childMenus);
        return pNode;
    }

    /**
     *  获取根节点
     * @author Cai.ChangJun
     * @return: 所有根节点
     * @version 1.0.0
     * @Date 2021/6/2 23:56
     */
    private List<ResourceTreeDTO> getRootNode() {
        List<ResourceTreeDTO> rootMenuLists =new  ArrayList<>();
        for(ResourceTreeDTO menuNode : resourceTreeDTOS1) {
            if(menuNode.getParentId() == 0) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }
}
