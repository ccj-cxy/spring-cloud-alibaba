package com.snk.file.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传接口
 * @author Cai.ChangJun
 * @version 1.0.0
 * @Date 2021/5/21 17:04
 */
public interface UploadFileService
{
    /**
     * 文件上传接口
     * 
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    public String uploadFile(MultipartFile file,String bucketName) throws Exception;

    /**
     * 下载
     * @author Cai.ChangJun
     * @param httpServletRequest :
     * @param httpServletResponse :
     * @param bucketName : 桶名称
     * @param objectName : 文件名称
     * @version 1.0.0
     * @Date 2021/5/21 23:47
     */
    void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                  String bucketName,  String objectName);
}
