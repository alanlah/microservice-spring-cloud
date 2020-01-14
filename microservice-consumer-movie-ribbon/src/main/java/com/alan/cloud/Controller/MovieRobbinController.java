package com.alan.cloud.Controller;

import com.alan.cloud.entity.User;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieRobbinController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @GetMapping("movie/{id}")
    public User findById(@PathVariable Long id) {
        System.out.println("start.............");
        // http://localhost:7900/simple/
        // VIP virtual IP
        // HAProxy Heartbeat
        // http://user-provider/ user-provicer服务名称
        return this.restTemplate.getForObject("http://user-provider/simple/" + id, User.class);
    }
    @GetMapping("/test")
    public String test(){
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("user-provider");
        System.out.println("user1:"+serviceInstance.getServiceId()+":"+serviceInstance.getHost()+":"+serviceInstance.getPort());
        serviceInstance = this.loadBalancerClient.choose("user-provider2");
        System.out.println("user2:"+serviceInstance.getServiceId()+":"+serviceInstance.getHost()+":"+serviceInstance.getPort());
        return "1";
    }
}
