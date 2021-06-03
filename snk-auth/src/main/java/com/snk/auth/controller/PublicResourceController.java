package com.snk.auth.controller;


import cn.hutool.core.bean.BeanUtil;
import com.snk.auth.pojo.domain.PublicResource;
import com.snk.auth.pojo.dto.ResourceTreeDTO;
import com.snk.auth.pojo.param.ResourceParam;
import com.snk.auth.service.PublicResourceService;
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
 * 资源表 前端控制器
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-28
 */
@RestController
@RequestMapping("/public-resource")
@Api(value = "系统资源信息",tags = "控制系统资源等信息")
public class PublicResourceController {

    @Autowired
    private PublicResourceService publicResourceService;

    @PostMapping("/add")
    @ApiOperation(value = "添加资源" ,notes = "新增系统资源")
    public PublicResource addResource(@RequestBody @Validated PublicResource resource) {
        publicResourceService.save(resource);
        return resource;
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改资源" ,notes = "修改系统资源")
    public PublicResource modifyResource(@RequestBody @Validated ResourceParam param) {
        PublicResource resource = new PublicResource();
        BeanUtil.copyProperties(param,resource);
        publicResourceService.updateById(resource);
        return resource;
    }

    @DeleteMapping("/remove")
    @ApiOperation(value = "删除资源" ,notes = "删除系统资源")
    public int removeResource(@RequestBody List<Integer> resourceIds) {
        publicResourceService.removeByIds(resourceIds);
        return resourceIds.size();
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取资源树" ,notes = "获取资源树")
    public List<ResourceTreeDTO> getTree() {
        return publicResourceService.getResourceTree();
    }








}

