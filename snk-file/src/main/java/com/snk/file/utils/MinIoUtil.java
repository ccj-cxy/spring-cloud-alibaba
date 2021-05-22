package com.snk.file.utils;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.PutObjectOptions;
import io.minio.RemoveBucketArgs;
import io.minio.Result;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Minio工具类
 */
@Slf4j
@Component
public  class MinIoUtil {
    @Autowired
    public  MinioClient minioClient;


    /**
     * 判断 bucket是否存在
     *
     * @param bucketName:
     *            桶名
     * @return: boolean
     */
    @SneakyThrows(Exception.class)
    public  boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(bucketName);
    }

    /**
     * 创建 bucket
     *
     * @param bucketName:
     *            桶名
     * @return: void
     */
    @SneakyThrows(Exception.class)
    public  void createBucket(String bucketName) {
        boolean isExist = minioClient.bucketExists(bucketName);
        if (!isExist) {
            minioClient.makeBucket(bucketName);
        }
    }

    /**
     * 获取全部bucket
     *
     * @param :
     * @return: java.util.List<io.minio.messages.Bucket>
     */
    @SneakyThrows(Exception.class)
    public  List<Bucket> getAllBuckets() {
        return minioClient.listBuckets();
    }

    /**删除存储桶*/
    @SneakyThrows
    public void removeBucket(String bucketName) {
        boolean flag = bucketExists(bucketName);
        Assert.isTrue(flag,"bucket不存在");
        Iterable<Result<Item>> myObjects = listObjects(bucketName);
        for (Result<Item> result : myObjects) {
            Item item = result.get();
            Assert.isTrue(item.size() > 0,"bucket有对象文件，删除失败");
        }
        // 删除存储桶，注意，只有存储桶为空时才能删除成功。
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        flag = bucketExists(bucketName);
        Assert.isTrue(flag,"bucket未成功删除");
    }

    /**列出存储桶中的所有对象名称*/
    @SneakyThrows
    public List<String> listObjectNames(String bucketName) {
        List<String> listObjectNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        Assert.isTrue(flag,"bucket不存在");
        Iterable<Result<Item>> myObjects = listObjects(bucketName);
        for (Result<Item> result : myObjects) {
            Item item = result.get();
            listObjectNames.add(item.objectName());
        }
        return listObjectNames;
    }

    /**列出存储桶中的所有对象*/
    @SneakyThrows
    public Iterable<Result<Item>> listObjects(String bucketName) {
        boolean flag = bucketExists(bucketName);
        Assert.isTrue(flag,"bucket不存在");
        return minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(bucketName)
                .recursive(false)
                .includeUserMetadata(false)
                .useApiVersion1(false)
                .build());
    }

    /**
     * 文件上传
     *
     * @param bucketName:
     *            桶名
     * @param fileName:
     *            文件名
     * @param filePath:
     *            文件路径
     * @return: void
     */
    @SneakyThrows(Exception.class)
    public  void upload(String bucketName, String fileName, String filePath) {
        minioClient.putObject(bucketName, fileName, filePath, null);
    }

    /**
     * 文件上传
     *
     * @param bucketName:
     *            桶名
     * @param fileName:
     *            文件名
     * @param stream:
     *            文件流
     * @return: java.lang.String : 文件url地址
     */
    @SneakyThrows(Exception.class)
    public  String upload(String bucketName, String fileName, InputStream stream) {
        minioClient.putObject(bucketName, fileName, stream, new PutObjectOptions(stream.available(), -1));
        return getFileUrl(bucketName, fileName);
    }

    /**
     * 文件上传
     *
     * @param bucketName:
     *            桶名
     * @param file:
     *            文件
     * @return: java.lang.String : 文件url地址
     */
    @SneakyThrows(Exception.class)
    public  String upload(String bucketName, MultipartFile file) {
        final InputStream is = file.getInputStream();
        final String fileName = file.getOriginalFilename();
        minioClient.putObject(bucketName, fileName, is, new PutObjectOptions(is.available(), -1));
        is.close();
        return getFileUrl(bucketName, fileName);
    }

    /**
     * 删除文件
     *
     * @param bucketName:
     *            桶名
     * @param fileName:
     *            文件名
     * @return: void
     */
    @SneakyThrows(Exception.class)
    public  void deleteFile(String bucketName, String fileName) {
        minioClient.removeObject(bucketName, fileName);
    }

    /**
     * 下载文件
     *
     * @param bucketName:
     *            桶名
     * @param fileName:
     *            文件名
     * @param response:
     * @return: void
     */
    @SneakyThrows(Exception.class)
    public  void download(String bucketName, String fileName, HttpServletResponse response) {
        // 获取对象的元数据
        final ObjectStat stat = minioClient.statObject(bucketName, fileName);
        response.setContentType(stat.contentType());
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        InputStream is = minioClient.getObject(bucketName, fileName);
        IOUtils.copy(is, response.getOutputStream());
        is.close();
    }

    /**
     * 获取minio文件的下载地址
     *
     * @param bucketName:
     *            桶名
     * @param fileName:
     *            文件名
     * @return: java.lang.String
     * @date : 2020/8/16 22:07
     */
    @SneakyThrows(Exception.class)
    public  String getFileUrl(String bucketName, String fileName) {
           String url =
                        minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(fileName)
                            .expiry(2, TimeUnit.HOURS)
                           .build());
        return minioClient.presignedGetObject(bucketName, fileName);
    }


    /**
     *
     * @Description 获取minio所有的桶
     * @return java.util.List<io.minio.messages.Bucket>
     **/
    public List<Bucket> getAllBucket() throws Exception {
        // 获取minio中所以的bucket
        List<Bucket> buckets = minioClient.listBuckets();
        for (Bucket bucket : buckets) {
            log.info("bucket 名称:  {}      bucket 创建时间: {}", bucket.name(), bucket.creationDate());
        }
        return buckets;
    }

    /**
     *
     * @Description 根据指定的objectName获取下载链接，需要bucket设置可下载的策略
     * @param objectName: 对象的名称
     * @return java.lang.String
     **/
    public String getUrlByObjectName(String bucketName,String objectName) {
        String objectUrl = null;
        try {
            objectUrl = minioClient.getObjectUrl(bucketName, objectName);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return objectUrl;
    }





}