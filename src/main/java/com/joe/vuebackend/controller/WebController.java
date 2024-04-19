package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.LoginInfo;
import com.joe.vuebackend.bean.RegisterInfo;
import com.joe.vuebackend.service.UserService;
import com.joe.vuebackend.vo.UserInfo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebController {

    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    @PostMapping("/login")
    public HttpResult<UserInfo> login(@RequestBody LoginInfo info) {
        return userService.login(info);
    }

    @PostMapping("/register")
    public HttpResult<String> register(@RequestBody RegisterInfo info) {
        return userService.register(info);
    }
}
