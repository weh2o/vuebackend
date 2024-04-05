package com.joe.vuebackend;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.RegisterInfo;
import com.joe.vuebackend.controller.WebController;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.utils.JwtUtil;
import com.joe.vuebackend.vo.UserInfo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@SpringBootTest
@Slf4j
public class webTest {

    @Setter(onMethod_ = @Autowired)
    WebController webController;

    @Test
    void loginTest() {
//        User user = new User();
//        user.setName("admin");
//        user.setPassword("1111");
//        HttpResult<UserInfo> result = webController.login(user);
//        log.error(result.toString());
    }

    @Test
    void encryption() {
        String p1 = DigestUtils.md5DigestAsHex("1111".getBytes(StandardCharsets.UTF_8));
        String p2 = DigestUtils.md5DigestAsHex("1111".getBytes(StandardCharsets.UTF_8));
        System.out.println(p1);
    }


    @Test
    void tokenTest() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZWI5OTA3NC05ODFlLTQ0NmItYjc4OC1hNzllOGI0OWIwNjgiLCJleHAiOjE3MTIxMTc0ODB9.79GcvsOqBJcUDejeDbdwfvINmVdXuBm4M0IfYHheekE";
        HttpResult<String> result = JwtUtil.checkSign(token);

        String id = JwtUtil.getUserId(token);
        System.out.println();
    }

    @Test
    void registerTest() {
        RegisterInfo info = new RegisterInfo();
        info.setName("測試");
        info.setPassword("33456");
        info.setNo("33456");
        webController.register(info);
    }
}
