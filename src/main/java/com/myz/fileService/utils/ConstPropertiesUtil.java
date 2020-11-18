package com.myz.fileService.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstPropertiesUtil implements InitializingBean {

	@Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endpoint;

	@Value("${spring.cloud.alicloud.access-key}")
    private String keyId;

	@Value("${spring.cloud.alicloud.secret-key}")
    private String keySecret;

	@Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucketName;

    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    //当上面变量初始化后会调用这个方法
    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endpoint;
        KEY_ID=keyId;
        KEY_SECRET=keySecret;
        BUCKET_NAME=bucketName;
    }
}
