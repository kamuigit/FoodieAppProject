package com.niit.FoodieApp.proxy;

import com.niit.FoodieApp.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="auth-service",url="http://localhost:8085")
public interface UserProxy {
    @PostMapping("/api/user/v1/register")
    public ResponseEntity customerRegister(@RequestBody User user);
}
