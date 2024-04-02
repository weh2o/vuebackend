package com.joe.vuebackend;

import com.joe.vuebackend.constant.RoleType;
import com.joe.vuebackend.domain.Role;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.repository.RoleRepository;
import com.joe.vuebackend.repository.UserRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InitDateTest {

    @Setter(onMethod_ = @Autowired)
    private RoleRepository roleRepository;

    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;

    @Test
    @Commit
    void initUserTest() {
        User user = new User();
        user.setName("admin");
        String password = DigestUtils.md5DigestAsHex("1111".getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);
        userRepository.save(user);
    }

    /**
     * 初始化資料庫中的角色
     */
    @Test
    @Commit
    @Order(1)
    void initRole() {
        List<Role> roleList = new ArrayList<>();
        for (RoleType roleType : RoleType.values()) {
            Role role = new Role();
            role.setName(roleType.getText());
            role.setNameZh(roleType.getTextZh());
            roleList.add(role);
        }
        roleRepository.saveAll(roleList);
    }
}

