package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.service.UserService;
import com.joe.vuebackend.vo.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    @GetMapping("/info")
    public HttpResult<UserInfo> getInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        return userService.getInfo(token);
    }

    @PatchMapping("{id}")
    public HttpResult<String> updateInfo(@PathVariable String id,
                                         @RequestBody UserInfo userInfo) {
        if (StringUtils.isNotEmpty(userInfo.getOldPassword())
                && StringUtils.isNotEmpty(userInfo.getNewPassword())
        ) {
            return userService.updatePassword(id, userInfo);
        } else {
            return userService.updateInfo(id, userInfo);
        }
    }
}
