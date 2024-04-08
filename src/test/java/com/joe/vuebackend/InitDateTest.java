package com.joe.vuebackend;

import com.joe.vuebackend.constant.Gender;
import com.joe.vuebackend.constant.IdentityType;
import com.joe.vuebackend.constant.RoleType;
import com.joe.vuebackend.domain.*;
import com.joe.vuebackend.repository.*;
import com.joe.vuebackend.utils.MenuHelper;
import com.joe.vuebackend.utils.RoleHelper;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Setter(onMethod_ = @Autowired)
    private IdentityRepository identityRepository;

    @Setter(onMethod_ = @Autowired)
    private TeacherNoRepository teacherNoRepository;

    @Setter(onMethod_ = @Autowired)
    private TeacherRepository teacherRepository;


    /**
     * 初始化身分
     */
    @Test
    @Commit
    @Order(1)
    void initIdentity() {
        ArrayList<Identity> list = new ArrayList<>();
        for (IdentityType identityType : IdentityType.values()) {
            Identity target = new Identity();
            target.setCode(identityType.getCode());
            target.setName(identityType.getText());
            target.setNameZh(identityType.getTextZh());
            list.add(target);
        }
        identityRepository.saveAll(list);
    }


    /**
     * 初始化角色(權限)
     */
    @Test
    @Commit
    @Order(2)
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

    @Test
    @Commit
    @Order(3)
    void initMenu() {
        MenuHelper.build(
                "首頁",
                "/home",
                "House",
                0,
                RoleType.getAllText()
        );

        MenuHelper.build(
                "學生管理",
                "/studentManagement",
                "User",
                1,
                List.of(RoleType.ADMIN.getText(),
                        RoleType.TEACHER.getText()
                )
        );

        MenuHelper.build(
                "課程管理",
                "/course",
                "Memo",
                2,
                List.of(RoleType.ADMIN.getText(),
                        RoleType.TEACHER.getText(),
                        RoleType.STUDENT.getText()
                )
        );
    }

    /**
     * 初始化使用者 - 管理員
     */
    @Test
    @Commit
    @Order(4)
    void initAdmin() {
        User user = new User();
        user.setAccount("admin");
        user.setName("超級管理員");
        user.setGender(Gender.BOY);
        user.setMail("admin@gmail.com");
        user.setPhone("0988123456");
        user.setAddress("無處不在");
        user.setBirth(LocalDate.of(2024, 4, 1));
        String password = DigestUtils.md5DigestAsHex("1111".getBytes(StandardCharsets.UTF_8));
        user.setPassword(password);

        // 角色權限
        List<Role> roleList = RoleHelper.getRoleList(Arrays.asList(
                RoleType.ADMIN.name(),
                RoleType.STUDENT.name()
        ));
        user.setRoles(roleList);

        User dbUser = userRepository.save(user);

        // 身分
        Optional<Identity> optional = identityRepository.findByName(IdentityType.ADMIN.getText());
        if (optional.isPresent()) {
            Identity identity = optional.get();
            List<User> userList = identity.getUserList();
            dbUser.setIdentity(identity);
            userList.add(dbUser);
            identity.setUserList(userList);
            identityRepository.save(identity);
        }
    }

    /**
     * 初始化學生
     */
    @Test
    @Commit
    @Order(5)
    void initStudent() {
        Student stu = new Student();
        stu.setAccount("stu");
        stu.setName("超級學生");
        stu.setGender(Gender.BOY);
        stu.setMail("stu@gmail.com");
        stu.setPhone("0988123456");
        stu.setAddress("睡在學校");
        stu.setBirth(LocalDate.of(2024, 4, 1));
        String password = DigestUtils.md5DigestAsHex("1111".getBytes(StandardCharsets.UTF_8));
        stu.setPassword(password);

        // 權限
        List<Role> roleList = RoleHelper.getRoleList(Arrays.asList(
                RoleType.STUDENT.name()
        ));
        stu.setRoles(roleList);
        Student dbStu = stuRepository.save(stu);

        // 身分
        Optional<Identity> optional = identityRepository.findByName(IdentityType.STUDENT.getText());
        if (optional.isPresent()) {
            Identity identity = optional.get();
            List<User> userList = identity.getUserList();
            dbStu.setIdentity(identity);
            userList.add(dbStu);
            identity.setUserList(userList);
            identityRepository.save(identity);
        }

    }

    /**
     * 初始化很多學生
     */
    @Test
    void initManyStudent() {
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

    @Test
    void initTeacherNo() {
        TeacherNo tNo = new TeacherNo();
        tNo.setNo("0000");
        tNo.setAvailable(Boolean.TRUE);
        teacherNoRepository.save(tNo);

        for (int i = 0; i < 5; i++) {
            TeacherNo no = new TeacherNo();
            no.setNo("t" + 100 + i);
            no.setAvailable(Boolean.TRUE);
            teacherNoRepository.save(no);
        }
    }

    @Test
    @Commit
    void initTeacher(){
        Teacher teacher = new Teacher();
        teacher.setAccount("teacher1");
        teacher.setPassword("1111");
        Optional<TeacherNo> no = teacherNoRepository.findByNo("0000");
        Teacher dbT = teacherRepository.save(teacher);
        if (no.isPresent()){
            dbT.setNo(no.get());
        }
        teacherRepository.save(dbT);
    }
}

