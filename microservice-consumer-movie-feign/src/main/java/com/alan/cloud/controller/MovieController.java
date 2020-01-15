package com.alan.cloud.controller;

import com.alan.cloud.feign.UserFeignClient;
import com.alan.common.entiry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {
    @Autowired
    private UserFeignClient userFeignClient;
    @GetMapping("/movie/{id}")
    public User findById(@PathVariable Long id) {
        return this.userFeignClient.findById(id);
    }
    @GetMapping("/test")
    public User testPost(User user) {
        return this.userFeignClient.postUser(user);
    }
    @RequestMapping(value = "/test-get",method = RequestMethod.GET)
    public User testGet(@RequestBody(required = false) User user) {
        return this.userFeignClient.getUser(user);
    }
}
