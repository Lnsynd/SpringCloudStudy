package com.test.service.client;

import com.test.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "userservice",fallback = UserFallbackClient.class)   // 为userservice服务提供的客户端
public interface UserClient {

    @RequestMapping("/user/{uid}")
    User findUserById(@PathVariable("uid") int uid);
}
