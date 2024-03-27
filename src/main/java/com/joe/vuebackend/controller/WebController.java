package com.joe.vuebackend.controller;

import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.service.UserService;
import com.joe.vuebackend.vo.HttpResult;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class WebController {


    @Setter(onMethod_ = @Autowired)
    private UserService userService;


    @PostMapping("/login")
    public HttpResult<User> login(@RequestBody User user){
        HttpResult<User> result = new HttpResult<>();
        Optional<User> optional = userService.login(user);
        if (optional.isPresent()){
            User target = optional.get();
            result = HttpResult.success("登入成功", target);
        }else {
            result.setCode(HttpStatus.UNAUTHORIZED.value());
            result.setMsg("登入失敗，請再次確認帳號與密碼");
        }
        System.out.println("被訪問了");
        return result;
    }
}
