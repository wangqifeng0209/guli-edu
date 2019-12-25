package com.atguigu.gulivideoservice.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Jason
 * @create 2019-12-20-14:19
 */

@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Bean
    public Docket webApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .build();

    }


    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("网站-视频上传")
                .description("本文档描述了视频上传微服务接口定义")
                .version("1.0")
                .contact(new Contact("jason", "http://atguigu.com", "475091947@qq.com"))
                .build();
    }
}
