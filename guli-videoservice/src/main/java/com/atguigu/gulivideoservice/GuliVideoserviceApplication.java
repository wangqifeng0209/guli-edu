package com.atguigu.gulivideoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GuliVideoserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuliVideoserviceApplication.class, args);
    }

}
