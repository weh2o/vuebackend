package com.joe.vuebackend;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.RegisterInfo;
import com.joe.vuebackend.constant.IdentityType;
import com.joe.vuebackend.domain.Identity;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.repository.IdentityRepository;
import com.joe.vuebackend.repository.UserRepository;
import com.joe.vuebackend.service.UserService;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserTest {

    @Setter(onMethod_ = @Autowired)
    private UserRepository repository;

    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    @Setter(onMethod_ = @Autowired)
    private IdentityRepository identityRepository;


    @Test
    void registerTeacher() {
        RegisterInfo info = new RegisterInfo();
        info.setAccount("t-test2");
        info.setPassword("1111");
        info.setNo("6666");
        info.setIdentity(IdentityType.TEACHER.getCode());

        HttpResult<String> result = userService.register(info);
        System.out.println(result);
    }

    @Test
    void registerStu() {
        // 註冊
        RegisterInfo info = new RegisterInfo();
        info.setAccount("textstu9");
        info.setPassword("1111");
        info.setNo("211");
        info.setIdentity(IdentityType.STUDENT.getCode());

        HttpResult<String> result = userService.register(info);
        List<User> list = new ArrayList<>();
        // 查看身分是否增加
        Optional<Identity> optional = identityRepository.findByName(IdentityType.STUDENT.getText());
        if (optional.isPresent()) {
            Identity identity = optional.get();
            list = identity.getUserList();
        }
        System.out.println(list);
        System.out.println(result);
    }
}
