package com.snk.oos.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.snk.oos.config.OssProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 阿里云上传服务工具类
 * @author: Cai.ChangJun
 * @date: 2021/7/18
 */
@Component
@Slf4j
public class AliyunOSSUtil {

    @Autowired
    private OssProperties oosProperties;
    
    public  String upload(File file){
        log.info("=========>OSS文件上传开始："+file.getName());
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        if(null == file){
            return null;
        }

        OSSClient ossClient = new OSSClient(oosProperties.getEndpoint(),oosProperties.getAccessKeyId(),oosProperties.getAccessKeySecret());
        try {
            //容器不存在，就创建
            if(! ossClient.doesBucketExist(oosProperties.getBucketName())){
                ossClient.createBucket(oosProperties.getBucketName());
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(oosProperties.getBucketName());
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = oosProperties.getDir()+"/"+(dateStr + "/" + UUID.randomUUID().toString().replace("-","")+"-"+file.getName());
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(oosProperties.getBucketName(), fileUrl, file));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(oosProperties.getBucketName(),CannedAccessControlList.PublicRead);
            if(null != result){
                log.info("==========>OSS文件上传成功,OSS地址："+fileUrl);
                return fileUrl;
            }
        }catch (OSSException oe){
            log.error(oe.getMessage());
        }catch (ClientException ce){
            log.error(ce.getMessage());
        }finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }

}
