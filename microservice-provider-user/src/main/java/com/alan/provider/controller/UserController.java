package com.alan.provider.controller;

import com.alan.provider.entity.User;
import com.alan.provider.repository.UserRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 模拟，根据ID获取User信息
     * @param id
     * @return
     */
    @GetMapping("/simple/{id}")
    public User findById(@PathVariable Long id){
        return this.userRepository.findOne(id);
    }

    /**
     *根据服务名称获取IP地址，端口号（http://192.168.1.7:7900/）
     * @return
     */
    @GetMapping("/eureka-instance")
    public String serviceUrl(){
        InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("USER-PROVIDER",false);
        return instance.getHomePageUrl();
    }

    /**
     * 获取ServiceInfo
     * {"host":"192.168.1.7","port":7900,"metadata":{},
     * "secure":false,"uri":"http://192.168.1.7:7900","serviceId":"user-provider"}
     * @return
     */
    @GetMapping("/instance-info")
    public ServiceInstance showInstance(){
        return this.discoveryClient.getLocalServiceInstance();
    }

}
