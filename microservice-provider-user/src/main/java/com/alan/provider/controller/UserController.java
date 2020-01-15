package com.alan.provider.controller;

import com.alan.provider.entity.User;
import com.alan.provider.repository.UserRepository;
import com.google.common.collect.Lists;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @PostMapping("/user")
    public User postUser(@RequestBody User user) {
        return user;
    }

    // 该请求不会成功
    @GetMapping("/get-user")
    public User getUser(@RequestBody  User user) {
        return user;
    }

    @GetMapping("list-all")
    public List<User> listAll() {
        ArrayList<User> list = Lists.newArrayList();
        User user = new User(1L, "zhangsan");
        User user2 = new User(2L, "zhangsan");
        User user3 = new User(3L, "zhangsan");
        list.add(user);
        list.add(user2);
        list.add(user3);
        return list;

    }
}
