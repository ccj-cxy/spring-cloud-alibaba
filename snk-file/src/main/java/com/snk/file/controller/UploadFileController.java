package com.snk.file.controller;

import com.snk.common.utils.file.FileUtils;
import com.snk.file.pojo.domain.UploadFile;
import com.snk.file.service.UploadFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件请求处理
 * 
 * @author ruoyi
 */
@RestController
@Api(value = "文件请求处理",tags = "上传文件")
public class UploadFileController
{
    private static final Logger log = LoggerFactory.getLogger(UploadFileController.class);

    @Autowired
    private UploadFileService uploadFile;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    @ApiOperation(value = "上传文件",notes = "上传文件")
    public UploadFile upload(MultipartFile file)
    {
        try
        {
            // 上传并返回访问地址
            String url = uploadFile.uploadFile(file);
            UploadFile sysFile = new UploadFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return sysFile;
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            return null;
        }
    }
}