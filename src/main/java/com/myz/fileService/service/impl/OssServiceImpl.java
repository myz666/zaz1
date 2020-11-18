package com.myz.fileService.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.myz.fileService.service.OssService;
import com.myz.fileService.utils.ConstPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvatar(MultipartFile file) {
        String endpoint = ConstPropertiesUtil.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstPropertiesUtil.KEY_ID;
        String accessKeySecret = ConstPropertiesUtil.KEY_SECRET;
        String bucketName=ConstPropertiesUtil.BUCKET_NAME;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            String fileName=file.getOriginalFilename();

            String uuid= UUID.randomUUID().toString().replaceAll("-","");
            fileName=uuid+fileName;
            String dataPath=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            fileName=dataPath+"/"+fileName;
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            //得到上传后的文件路径
            String url="https://"+bucketName+"."+endpoint+"/"+fileName;
            //返回上传后的文件路径
            return url;
        }catch(Exception e){
            e.printStackTrace();

        }
        return null;
    }


    //删除文件
    @Override
    public void deleteFile(String dirPath,String fileName) {
        String endpoint = ConstPropertiesUtil.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstPropertiesUtil.KEY_ID;
        String accessKeySecret = ConstPropertiesUtil.KEY_SECRET;
        String bucketName=ConstPropertiesUtil.BUCKET_NAME;

        String totalFileName = dirPath+"/"+fileName;
//        System.out.println(fileName);

        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.deleteObject(bucketName,totalFileName);
            ossClient.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
