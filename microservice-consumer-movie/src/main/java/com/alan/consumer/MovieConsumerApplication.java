package com.alan.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieConsumerApplication.class,args);
    }
}
