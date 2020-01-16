package com.alan.cloud.feign;

import com.alan.common.entiry.User;
import com.alan.config.FooConfiguration;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "user-provider",configuration = FooConfiguration.class)
public interface UserFeignClient {
    @RequestLine("GET /simple/{id}")
    public User findById(@Param("id") Long id);
}
