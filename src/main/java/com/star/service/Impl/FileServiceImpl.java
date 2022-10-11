package com.star.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.star.service.FileService;
import com.star.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 *
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        String uploadUrl = null;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            //获取文件名
            String fileName = file.getOriginalFilename();

            //生成随机字符串拼接文件名，避免同名文件被覆盖
            fileName = UUID.randomUUID().toString().replace("-", "").concat(fileName);

            //将文件按照日期进行归类
            String path = new DateTime().toString("yyyy/MM/dd").concat("/").concat(fileName);

            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, path, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //拼接访问路径
            uploadUrl = "http://".concat(bucketName).concat(".").concat(endPoint).concat("/").concat(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadUrl;
    }
}
