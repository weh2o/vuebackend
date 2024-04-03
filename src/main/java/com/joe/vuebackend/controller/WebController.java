package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.RegisterInfo;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.service.UserService;
import com.joe.vuebackend.utils.JwtUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class WebController {

    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    @PostMapping("/login")
    public HttpResult<String> login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/register")
    public HttpResult<String> register(@RequestBody RegisterInfo info) {
        return userService.register(info);
    }

    @GetMapping("/tokenValidate/{token}")
    public HttpResult<String> tokenValidate(@PathVariable String token) {
        return JwtUtil.checkSign(token);
    }
}
