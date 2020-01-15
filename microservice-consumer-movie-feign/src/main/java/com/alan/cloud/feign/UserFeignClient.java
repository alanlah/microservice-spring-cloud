package com.alan.cloud.feign;

import com.alan.common.entiry.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("user-provider")
public interface UserFeignClient {
    // 两个坑：1. @GetMapping不支持   2. @PathVariable得设置value
    @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User postUser(@RequestBody User user);

    // 该请求不会成功，只要参数是复杂对象，即使指定了是GET方法，feign依然会以POST方法进行发送请求。
    @RequestMapping(value = "/get-user", method = RequestMethod.GET, consumes="application/json")
    public User getUser(@RequestBody User user);

}
