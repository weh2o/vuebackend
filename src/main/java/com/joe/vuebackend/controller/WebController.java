package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.LoginInfo;
import com.joe.vuebackend.bean.RegisterInfo;
import com.joe.vuebackend.constant.CacheConstant;
import com.joe.vuebackend.constant.URLConstant;
import com.joe.vuebackend.service.UserService;
import com.joe.vuebackend.vo.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebController {

    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    @PostMapping(URLConstant.LOGIN)
    public HttpResult<UserInfo> login(@RequestBody LoginInfo info) {
        return userService.login(info);
    }

    @PostMapping(URLConstant.REGISTER)
    public HttpResult<String> register(@RequestBody RegisterInfo info) {
        return userService.register(info);
    }

    @GetMapping(URLConstant.LOGOUT)
    public HttpResult<String> logout(HttpServletRequest request){
        return userService.logout(request.getHeader(CacheConstant.TOKEN));
    }
}
