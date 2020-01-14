package com.alan.cloud;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ExcludeFromComponentScan
public class TestConfiguration {
    /*
        自定义ribbon负载均衡策略，随机
     */
    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }
}
