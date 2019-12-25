package com.atguigu.gulivideoservice.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 初始化常量的工具类
 * @author Jason
 * @create 2019-12-20-13:38
 */

@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${aliyun.oss.key-id}")
    private String keyId;

    @Value("${aliyun.oss.key-secret}")
    private String keySecret;

    public static String KEYID;

    public static String KEYSECRET;
    @Override
    public void afterPropertiesSet() throws Exception {
        KEYID = keyId;
        KEYSECRET= keySecret;
    }
}
