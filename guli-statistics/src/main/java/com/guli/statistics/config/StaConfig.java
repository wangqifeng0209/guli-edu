package com.guli.statistics.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Jason
 * @create 2019-12-21-19:58
 */

@Configuration
@EnableTransactionManagement
@MapperScan("com.guli.statistics.mapper")
public class StaConfig {
}
