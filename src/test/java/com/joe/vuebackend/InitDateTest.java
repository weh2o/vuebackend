package com.joe.vuebackend;

import com.joe.vuebackend.constant.Gender;
import com.joe.vuebackend.constant.RoleType;
import com.joe.vuebackend.domain.Role;
import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.repository.RoleRepository;
import com.joe.vuebackend.repository.StudentRepository;
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
import java.time.LocalDate;
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

    @Setter(onMethod_ = @Autowired)
    private StudentRepository stuRepository;

    @Test
    @Commit
    void initUser() {
        User user = new User();
        user.setAccount("admin");
        user.setName("超級管理員");
        String password = DigestUtils.md5DigestAsHex("1111".getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);
        userRepository.save(user);
    }

    @Test
    void initStudent() {
        for (int i = 0; i < 50; i++) {
            Student target = new Student();
            target.setName("機器人" + i);
            target.setAge(i);
            target.setNo(10000 + i + "");
            if (i % 2 == 0) {
                target.setGender(Gender.BOY);
            } else {
                target.setGender(Gender.GIRL);
            }

            target.setBirth(LocalDate.now());
            target.setPhone("098710000" + i);
            target.setMail(i + "newJJ@gmail.com");
            stuRepository.save(target);
        }
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

