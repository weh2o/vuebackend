package com.joe.vuebackend;

import com.joe.vuebackend.constant.Gender;
import com.joe.vuebackend.constant.IdentityType;
import com.joe.vuebackend.constant.RoleType;
import com.joe.vuebackend.domain.*;
import com.joe.vuebackend.repository.*;
import com.joe.vuebackend.utils.DateUtil;
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
import java.time.Period;
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

    /**
     * 初始化清單
     */
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

        MenuHelper.build(
                "公告管理",
                "/announcement",
                "Tickets",
                3,
                List.of(RoleType.ADMIN.getText()
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
        // 基本資料
        user.setName("超級管理員");
        user.setGender(Gender.BOY);
        user.setBirth(LocalDate.of(2000, 1, 1));
        user.setMail("admin@gmail.com");
        user.setPhone("0988123456");
        user.setAddress("無處不在");
        // 帳密
        user.setAccount("admin");
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
     * 初始化使用者 - 學生
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
        stu.setBirth(LocalDate.of(2020, 4, 1));
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

        Optional<Identity> optional = identityRepository.findByName(IdentityType.STUDENT.getText());
        Identity identity = optional.get();
        List<User> userList = identity.getUserList();

        List<Role> roleList = RoleHelper.getRoleList(Arrays.asList(
                RoleType.STUDENT.name()
        ));

        for (int i = 1; i <= 50; i++) {
            Student target = new Student();
            // 基本資料
            target.setName("機器人" + i);
            target.setNo(10000 + i + "");
            if (i % 3 == 0) {
                target.setGender(Gender.BOY);
            } else if (i % 4 == 0) {
                target.setGender(Gender.GIRL);
            } else {
                target.setGender(Gender.UNKNOWN);
            }

            // 生日
            LocalDate birth = DateUtil.getRangeLocalDate(
                    LocalDate.of(1994, 1, 1),
                    LocalDate.of(2008, 1, 1)
            );
            target.setBirth(birth);

            // 年紀
            LocalDate now = LocalDate.now();
            Period period = Period.between(birth, now);
            target.setAge(period.getYears());

            if (i >= 10) {
                target.setPhone("09871000" + i);
            } else {
                target.setPhone("098710000" + i);
            }
            target.setMail(i + "newJJ@gmail.com");

            Student dbStu = stuRepository.save(target);

            // 身分
            dbStu.setIdentity(identity);
            userList.add(dbStu);
            identity.setUserList(userList);

            // 權限
            dbStu.setRoles(roleList);
            identityRepository.save(identity);
        }
    }

    /**
     * 初始化教師證
     */
    @Test
    void initTeacherNo() {
        // 此為測試用教師證，永不設成false
        TeacherNo tNo = new TeacherNo();
        tNo.setNo("test0000");
        tNo.setAvailable(Boolean.TRUE);
        teacherNoRepository.save(tNo);

        for (int i = 0; i < 5; i++) {
            TeacherNo no = new TeacherNo();
            no.setNo("t" + 100 + i);
            no.setAvailable(Boolean.TRUE);
            teacherNoRepository.save(no);
        }
    }

    /**
     * 初始化使用者 - 老師
     */
    @Test
    @Commit
    void initTeacher() {

        Optional<TeacherNo> no = teacherNoRepository.findByNo("test0000");
        if (no.isPresent()) {
            Teacher teacher = new Teacher();
            teacher.setName("超級老師");
            teacher.setAccount("teacher");
            teacher.setGender(Gender.BOY);
            teacher.setMail("teacher@gmail.com");
            teacher.setPhone("0988123456");
            teacher.setAddress("睡在學校");
            teacher.setBirth(LocalDate.of(1980, 1, 1));
            String password = DigestUtils.md5DigestAsHex("1111".getBytes(StandardCharsets.UTF_8));
            teacher.setPassword(password);


            Teacher dbT = teacherRepository.save(teacher);

            // 教師證
            dbT.setNo(no.get());

            // 權限
            List<Role> roleList = RoleHelper.getRoleList(Arrays.asList(
                    RoleType.TEACHER.name()
            ));
            dbT.setRoles(roleList);

            // 身分
            Optional<Identity> optional = identityRepository.findByName(IdentityType.TEACHER.getText());
            if (optional.isPresent()) {
                Identity identity = optional.get();
                List<User> userList = identity.getUserList();
                dbT.setIdentity(identity);
                userList.add(dbT);
                identity.setUserList(userList);
                identityRepository.save(identity);
            }
        }
    }
}

