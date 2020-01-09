package com.alan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer//启动服务
public class EurekaApplication {
    public static void main(String[] args) {
        System.out.println("hello eureka");
        SpringApplication.run(EurekaApplication.class,args);
    }
}
