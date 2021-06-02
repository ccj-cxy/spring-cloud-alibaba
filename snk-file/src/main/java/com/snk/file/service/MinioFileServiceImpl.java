package com.snk.file.service;

import com.snk.file.config.MinioConfig;
import com.snk.file.utils.MinIoUtil;
import io.minio.MinioClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Minio 文件存储
 * 
 * @author ruoyi
 */
@Service
public class MinioFileServiceImpl implements UploadFileService
{
    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioClient client;

    @Autowired
    private MinIoUtil minIoUtil;

    /**
     * 本地文件上传接口
     * 
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file,String bucketName) throws Exception
    {
        //检查是否传了bucketName,没传使用默认配置的文件桶
        if (StringUtils.isEmpty(bucketName)) {
            bucketName= minioConfig.getBucketName();
        }
        //检查是否有对应的文件桶
        if (!minIoUtil.bucketExists(bucketName)) {
            minIoUtil.createBucket(bucketName);
        }
        minIoUtil.upload(bucketName,file.getOriginalFilename(),file);
        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + file.getOriginalFilename();
    }

    @Override
    public void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String bucketName, String objectName) {
        minIoUtil.download(bucketName, objectName,httpServletResponse);
    }
}
