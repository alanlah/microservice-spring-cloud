package com.alan.cloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import com.alan.config.FooConfiguration1;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "xxxx", url = "http://localhost:8761/", configuration = FooConfiguration1.class)
public interface FeignClient2 {
    @RequestMapping(value = "/eureka/apps/{serviceName}")
    public String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName") String serviceName);
}

