package com.joe.vuebackend;

import com.joe.vuebackend.controller.WebController;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.vo.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class webTest {

    @Autowired
    WebController webController;

    @Test
    void loginTest() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("qwer1234");
        HttpResult<User> result = webController.login(user);
        log.error(result.toString());

    }
}
