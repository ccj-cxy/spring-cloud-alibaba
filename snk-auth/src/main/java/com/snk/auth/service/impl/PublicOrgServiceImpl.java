package com.snk.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snk.auth.dao.PublicOrgMapper;
import com.snk.auth.pojo.domain.PublicOrg;
import com.snk.auth.pojo.dto.OrgTreeDTO;
import com.snk.auth.service.PublicOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门机构  服务实现类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-06-06
 */
@Service
public class PublicOrgServiceImpl extends ServiceImpl<PublicOrgMapper, PublicOrg> implements PublicOrgService {

    @Autowired
    private PublicOrgMapper orgMapper;

    private volatile List<OrgTreeDTO> orgs = new ArrayList<>();

    @Override
    public List<OrgTreeDTO> getOrgTree(Integer orgId) {
        List<PublicOrg> publicOrgs = orgMapper.selectList(null);
        publicOrgs.forEach(publicOrg -> {
            OrgTreeDTO orgTreeDTO = new OrgTreeDTO();
            BeanUtil.copyProperties(publicOrg,orgTreeDTO);
            orgs.add(orgTreeDTO);
        });
        List<OrgTreeDTO> orgTreeDTOS = buildTree(orgId);
        orgs.clear();
        return orgTreeDTOS;
    }

    /**
     * 构建树结构
     * @author Cai.ChangJun
     * @return: 整个树
     * @version 1.0.0
     * @Date 2021/6/2 23:55
     */
    public List<OrgTreeDTO> buildTree(Integer orgId){
        List<OrgTreeDTO> treeMenus =new  ArrayList<>();
        List<OrgTreeDTO> rootNode = getRootNode(orgId);
        for(OrgTreeDTO menuNode : rootNode) {
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
    private OrgTreeDTO buildChildTree(OrgTreeDTO pNode){
        List<OrgTreeDTO> childMenus =new  ArrayList<>();
        for(OrgTreeDTO menuNode : orgs) {
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
    private List<OrgTreeDTO> getRootNode(Integer orgId) {
        List<OrgTreeDTO> rootMenuLists =new  ArrayList<>();
        for(OrgTreeDTO menuNode : orgs) {
            if(menuNode.getParentId().equals(orgId)) {
                rootMenuLists.add(menuNode);
            }
        }
        return rootMenuLists;
    }
}
