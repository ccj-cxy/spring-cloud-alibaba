package com.snk.file.controller;

import com.snk.common.utils.file.FileUtils;
import com.snk.file.pojo.domain.UploadDTO;
import com.snk.file.pojo.domain.UploadFile;
import com.snk.file.service.UploadFileService;
import com.snk.file.utils.MinIoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件请求处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/file")
@Api(value = "文件请求处理",tags = "上传文件")
public class UploadFileController
{
    private static final Logger log = LoggerFactory.getLogger(UploadFileController.class);

    @Autowired
    private UploadFileService uploadFile;
    @Autowired
    private MinIoUtil minIoUtil;

    /**
     * 文件上传请求
     */
    @PostMapping("/upload")
    @ApiOperation(value = "上传文件",notes = "上传文件")
    public UploadFile upload(UploadDTO uploadDTO)
    {
        try
        {
            // 上传并返回访问地址
            String url = uploadFile.uploadFile(uploadDTO.getFile(),uploadDTO.getBucketName());
            UploadFile sysFile = new UploadFile();
            sysFile.setName(FileUtils.getName(url));
            sysFile.setUrl(url);
            return sysFile;
        }
        catch (Exception e)
        {
            log.error("上传文件失败", e);
            throw new RuntimeException("上传文件失败");
        }
    }

    @GetMapping("/download/{bucketName}/{objectName}")
    @ApiOperation(value = "下载文件",notes = "根据文件桶，文件名称下载文件")
    public void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         @PathVariable("bucketName") String bucketName,@PathVariable("objectName") String objectName) {
       uploadFile.download(httpServletRequest,httpServletResponse,bucketName,objectName);
    }


    @GetMapping("/getFileUrl/{bucketName}/{fileName}")
    @ApiOperation(value = "文件分享",notes = "根据文件桶，文件名称获取文件下载路径,并默认两个小时有效")
    public String getFileUrl(@PathVariable("bucketName")String bucketName,@PathVariable("fileName") String fileName) {
        return minIoUtil.getFileUrl(bucketName,fileName);
    }

    @DeleteMapping("/delete/{bucketName}/{fileName}")
    @ApiOperation(value = "删除文件",notes = "根据文件桶，文件名称删除文件")
    public void deleteFile(@PathVariable("bucketName")String bucketName,@PathVariable("fileName") String fileName) {
        minIoUtil.deleteFile(bucketName,fileName);
    }

    @GetMapping("/checkTheFileName/{bucketName}/{fileName}")
    @ApiOperation(value = "检查文件名称",notes = "根据文件桶，文件名称检查文件名称")
    public Boolean checkTheFileName(@PathVariable("bucketName")String bucketName,@PathVariable("fileName") String fileName) {
        List<String> strings = minIoUtil.listObjectNames(bucketName);
        return strings.contains(fileName);
    }
}