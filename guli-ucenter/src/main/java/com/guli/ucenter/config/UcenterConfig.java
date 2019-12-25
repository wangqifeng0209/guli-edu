package com.guli.ucenter.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Jason
 * @create 2019-12-21-19:44
 */

@Configuration
@EnableTransactionManagement
@MapperScan("com.guli.ucenter.mapper")
public class UcenterConfig {
}
