package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.service.UserService;
import com.joe.vuebackend.utils.JwtUtil;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class WebController {


    @Setter(onMethod_ = @Autowired)
    private UserService userService;


    @PostMapping("/login")
    public HttpResult<String> login(@RequestBody User user) {
        HttpResult<String> result = new HttpResult<>();
        String token = userService.login(user);
        if (StringUtils.isNotEmpty(token)) {
            result = HttpResult.success("登入成功", token);
        } else {
            result.setCode(HttpStatus.UNAUTHORIZED.value());
            result.setMsg("登入失敗，請再次確認帳號與密碼");
        }
        return result;
    }

    @GetMapping("/tokenValidate/{token}")
    public HttpResult<String> tokenValidate(@PathVariable String token) {
        return JwtUtil.checkSign(token);
    }
}
