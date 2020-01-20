package com.alan.cloud.controller;

import com.alan.common.entiry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@RestController
public class MovieController {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private LoadBalancerClient loadBalancerClient;

  @GetMapping("/movie/{id}")
  public User findById(@PathVariable Long id) {
    // http://localhost:7900/simple/
    // VIP virtual IP
    // HAProxy Heartbeat

    ServiceInstance serviceInstance = this.loadBalancerClient.choose("user-provider");
    System.out.println("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

    return this.restTemplate.getForObject("http://user-provider/simple/" + id, User.class);
  }

  @GetMapping("/test")
  public String test() {
    ServiceInstance serviceInstance = this.loadBalancerClient.choose("user-provider");
    System.out.println("user1" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

    ServiceInstance serviceInstance2 = this.loadBalancerClient.choose("user-provider2");
    System.out.println("user2" + ":" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());

    return "1";
  }
  @GetMapping("/list-all")
  public List<User> listAll() {
    // wrong
    //    List<User> list = this.restTemplate.getForObject("http://microservice-provider-user/list-all", List.class);
    //    for (User user : list) {
    //      System.out.println(user.getId());
    //    }

    // right
    User[] users = this.restTemplate.getForObject("http://user-provider/list-all", User[].class);
    List<User> list2 = Arrays.asList(users);
    for (User user : list2) {
      System.out.println(user.getId());
    }

    return list2;
  }
}
