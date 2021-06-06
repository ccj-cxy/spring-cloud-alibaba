package com.snk.auth.controller;


import cn.hutool.core.bean.BeanUtil;
import com.snk.auth.pojo.domain.PublicOrg;
import com.snk.auth.pojo.dto.OrgTreeDTO;
import com.snk.auth.pojo.param.OrgParam;
import com.snk.auth.service.PublicOrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 部门机构  前端控制器
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/public-org")
@Api(value = "系统组织信息",tags = "控制系统组织等信息")
public class PublicOrgController {

    @Autowired
    private PublicOrgService publicOrgService;

    @PostMapping("/add")
    @ApiOperation(value = "添加组织" ,notes = "新增系统组织")
    public PublicOrg addOrg(@RequestBody @Validated PublicOrg Org) {
        publicOrgService.save(Org);
        return Org;
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改组织" ,notes = "修改系统组织")
    public PublicOrg modifyOrg(@RequestBody @Validated OrgParam param) {
        PublicOrg Org = new PublicOrg();
        BeanUtil.copyProperties(param,Org);
        publicOrgService.updateById(Org);
        return Org;
    }

    @DeleteMapping("/remove")
    @ApiOperation(value = "删除组织" ,notes = "删除系统组织")
    public int removeOrg(@RequestBody List<Integer> OrgIds) {
        publicOrgService.removeByIds(OrgIds);
        return OrgIds.size();
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取组织树" ,notes = "获取组织树")
    public List<OrgTreeDTO> getTree() {
        return publicOrgService.getOrgTree();
    }
}

