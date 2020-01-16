package com.alan.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FooConfiguration {
    //使用Feign自己的注解，使用springmvc的注解就会报错
    @Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
    }
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;//配置日志
    }
}
