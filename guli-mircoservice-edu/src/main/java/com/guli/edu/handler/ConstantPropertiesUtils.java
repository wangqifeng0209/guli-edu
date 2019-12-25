package com.guli.edu.handler;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Jason
 * @create 2019-12-12-21:29
 */

//@Api("获取配置文件属性的工具类")
@Component
//@PropertySource("classpath:application.properties")
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.key-id}")
    private String keyId;

    @Value("${aliyun.oss.key-secret}")
    private String keySecret;

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;

    public static String ENDPOINT;

    public static String KEYID;

    public static String KEYSECRET;

    public static String BUCKETNAME;


    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        KEYID = keyId;
        KEYSECRET = keySecret;
        BUCKETNAME = bucketName;
    }
}
