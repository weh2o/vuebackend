package com.joe.vuebackend;

import com.joe.vuebackend.bean.InitMenuBean;
import com.joe.vuebackend.constant.CourseLocationType;
import com.joe.vuebackend.constant.Gender;
import com.joe.vuebackend.constant.IdentityType;
import com.joe.vuebackend.constant.RoleType;
import com.joe.vuebackend.domain.*;
import com.joe.vuebackend.repository.*;
import com.joe.vuebackend.utils.CRUDLogHelper;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class InitDataTest {

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

    @Setter(onMethod_ = @Autowired)
    private CourseLocationRepository locationRepository;

    @Setter(onMethod_ = @Autowired)
    private CourseRepository courseRepository;

    @Setter(onMethod_ = @Autowired)
    private PasswordEncoder passwordEncoder;

    /**
     * 初始化身分
     */
    @Test
    @Commit
    @Order(1)
    void initIdentities() {
        CRUDLogHelper logHelper = CRUDLogHelper.build(" 初始化身分 initIdentities()");
        for (IdentityType identityType : IdentityType.values()) {
            String text = identityType.getText();
            try {
                boolean exists = identityRepository.existsByName(text);
                if (!exists) {
                    Identity target = new Identity();
                    target.setCode(identityType.getCode());
                    target.setName(text);
                    target.setNameZh(identityType.getTextZh());
                    identityRepository.save(target);
                    logHelper.saveSuccess(text);
                } else {
                    logHelper.saveDuplicateFail(text);
                }
            } catch (Exception e) {
                logHelper.saveFail(text, e);
            }
        }
    }


    /**
     * 初始化角色權限
     */
    @Test
    @Commit
    @Order(2)
    void initRoles() {
        CRUDLogHelper logHelper = CRUDLogHelper.build("初始化角色權限 initRoles()");
        for (RoleType roleType : RoleType.values()) {
            String text = roleType.getText();
            boolean exists = roleRepository.existsByName(text);
            try {
                if (!exists) {
                    Role role = new Role();
                    role.setName(text);
                    role.setNameZh(roleType.getTextZh());
                    roleRepository.save(role);
                    logHelper.saveSuccess(text);
                } else {
                    logHelper.saveDuplicateFail(text);
                }
            } catch (Exception e) {
                logHelper.saveFail(text, e);
            }
        }
    }

    /**
     * 初始化清單
     */
    @Test
    @Commit
    @Order(3)
    void initMenus() {
        List<InitMenuBean> list = MenuHelper.getAllInitMenuBean();
        MenuHelper.build(list);
    }

    /**
     * 初始化使用者 - 管理員
     */
    @Test
    @Commit
    @Order(4)
    void initAdmin() {
        CRUDLogHelper logHelper = CRUDLogHelper.build("初始化使用者 - 管理員 initAdmin()");
        boolean exists = userRepository.existsByAccount("admin");
        if (!exists) {
            try {
                User user = new User();
                // 基本資料
                String name = "超級管理員";
                user.setName(name);
                user.setGender(Gender.BOY);
                user.setBirth(LocalDate.of(2000, 1, 1));
                user.setAge(user.getAge());
                user.setMail("admin@gmail.com");
                user.setPhone("0988123456");
                user.setAddress("無處不在");
                // 帳密
                user.setAccount("admin");
                user.setPassword(passwordEncoder.encode("1111"));

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
                logHelper.saveSuccess("admin");
            } catch (Exception e) {
                logHelper.saveFail("admin", e);
            }
        } else {
            logHelper.saveFail("admin");
        }
    }

    /**
     * 初始化使用者 - 學生
     */
    @Test
    @Commit
    @Order(5)
    void initStudent() {
        CRUDLogHelper logHelper = CRUDLogHelper.build("初始化使用者 - 學生 initStudent()");
        boolean exists = userRepository.existsByAccount("stu");
        if (!exists) {
            try {
                Student stu = new Student();
                stu.setAccount("stu");
                stu.setName("超級學生");
                stu.setGender(Gender.BOY);
                stu.setMail("stu@gmail.com");
                stu.setPhone("0988123456");
                stu.setAddress("睡在學校");
                stu.setBirth(LocalDate.of(2020, 4, 1));
                stu.setAge(stu.getAge());
                stu.setPassword(passwordEncoder.encode("1111"));

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
                logHelper.saveSuccess("stu");
            } catch (Exception e) {
                logHelper.saveFail("stu", e);
            }
        } else {
            logHelper.saveDuplicateFail("stu");
        }
    }

    /**
     * 初始化很多學生
     */
    @Test
    @Order(6)
    @Commit
    void initStudents() {
        CRUDLogHelper logHelper = CRUDLogHelper.build("初始化很多學生 initStudents()");
        Optional<Identity> optional = identityRepository.findByName(IdentityType.STUDENT.getText());
        if (optional.isPresent()) {
            Identity identity = optional.get();
            List<User> userList = identity.getUserList();

            List<Role> roleList = RoleHelper.getRoleList(Arrays.asList(
                    RoleType.STUDENT.name()
            ));

            for (int i = 1; i <= 50; i++) {
                String name = "機器人" + i;
                boolean exists = userRepository.existsByName(name);
                if (!exists) {
                    Student target = new Student();
                    try {
                        // 基本資料
                        target.setName(name);
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
                        logHelper.saveSuccess(name);
                    } catch (Exception e) {
                        logHelper.saveFail(name, e);
                    }
                } else {
                    logHelper.saveDuplicateFail(name);
                }
            }
        } else {
            String msg = String.format("Identity資料庫中的學生身分不存在");
            log.error(msg);
        }
    }

    /**
     * 初始化教師證
     */
    @Test
    @Commit
    @Order(7)
    void initTeacherNos() {
        CRUDLogHelper logHelper = CRUDLogHelper.build("初始化教師證 initTeacherNos()");
        for (int i = 0; i <= 10; i++) {
            String no = "t" + (100 + i);
            boolean exists = teacherNoRepository.existsByNo(no);
            if (!exists) {
                try {
                    TeacherNo teacherNo = new TeacherNo();
                    // t100x
                    teacherNo.setNo(no);
                    teacherNo.setAvailable(Boolean.TRUE);
                    teacherNoRepository.save(teacherNo);
                    logHelper.saveSuccess(no);
                } catch (Exception e) {
                    logHelper.saveFail(no, e);
                }
            } else {
                logHelper.saveDuplicateFail(no);
            }
        }
    }

    /**
     * 初始化使用者 - 老師
     */
    @Test
    @Commit
    @Order(8)
    void initTeacher() {
        String logTitle = "初始化使用者 - 老師 initTeacher()";
        CRUDLogHelper logHelper = CRUDLogHelper.build(logTitle);

        String account = "teacher";
        String password = "1111";
        String findTeacherNo = "t100";

        boolean exists = userRepository.existsByAccount(account);
        if (!exists) {
            // 教師證
            Optional<TeacherNo> no = teacherNoRepository.findByNo(findTeacherNo);
            if (no.isPresent()) {
                TeacherNo teacherNo = no.get();
                if (teacherNo.getAvailable()) {
                    Teacher teacher = new Teacher();
                    teacher.setName("超級老師");
                    teacher.setAccount(account);
                    teacher.setGender(Gender.BOY);
                    teacher.setMail("teacher@gmail.com");
                    teacher.setPhone("0988123456");
                    teacher.setAddress("睡在學校");
                    teacher.setBirth(LocalDate.of(1994, 1, 1));
                    teacher.setAge(teacher.getAge());
                    teacher.setPassword(passwordEncoder.encode(password));
                    Teacher dbT = teacherRepository.save(teacher);

                    // 教師證
                    dbT.setNo(teacherNo);
                    teacherNo.setAvailable(false);

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
                    logHelper.saveSuccess(account);
                    return;
                }

                String msg = String.format("%s 創建失敗，教師證 %s 已被使用", logTitle, teacherNo.getNo());
                log.error(msg);
                return;
            }
            String msg = String.format("%s 創建失敗，教師證 %s 不存在", logTitle, findTeacherNo);
            log.error(msg);
        } else {
            logHelper.saveDuplicateFail(account);
        }
    }

    /**
     * 初始化所有地點
     */
    @Test
    @Commit
    @Order(9)
    void initLocations() {
        CRUDLogHelper logHelper = CRUDLogHelper.build("初始化所有地點 initLocations()");
        for (CourseLocationType type : CourseLocationType.values()) {
            String code = type.getCode();
            String name = type.getName();
            String nameZh = type.getNameZh();
            boolean exists = locationRepository.existsByCode(code);
            if (!exists) {
                try {
                    CourseLocation location = new CourseLocation();
                    location.setCode(code);
                    location.setName(name);
                    location.setNameZh(nameZh);
                    locationRepository.save(location);
                    logHelper.saveSuccess(nameZh);
                } catch (Exception e) {
                    logHelper.saveFail(nameZh, e);
                }
            } else {
                logHelper.saveDuplicateFail(nameZh);
            }
        }
    }

    /**
     * 初始化課程
     */
    @Test
    @Commit
    @Order(10)
    void initCourse() {
        CRUDLogHelper logHelper = CRUDLogHelper.build("初始化課程 initCourse()");
        String name = "如何不被老婆發現私房錢";
        boolean exists = courseRepository.existsByName(name);
        if (!exists) {
            try {
                Course course = new Course();
                course.setName(name);
                course.setDeadline(LocalDate.of(2030, 1, 1));
                course.setMaxCount(30);
                course.setStartDate(LocalDate.now());
                course.setEndDate(LocalDate.of(2030, 1, 1));
                course.setStartTime(LocalTime.of(14, 0));
                course.setEndTime(LocalTime.of(15, 0));
                Course dbCourse = courseRepository.save(course);

                String teacherName = "超級老師";
                List<Teacher> teacherList = teacherRepository.findByName(teacherName);
                if (teacherList.size() == 0) {
                    throw new RuntimeException(teacherName + " 不存在");
                }
                dbCourse.setTeacher(teacherList.get(0));

                Optional<CourseLocation> locationOptional = locationRepository.findByName(CourseLocationType.LIBRARY.getName());
                if (locationOptional.isPresent()) {
                    dbCourse.setLocation(locationOptional.get());
                }
                courseRepository.save(dbCourse);
                logHelper.saveSuccess(name);
            } catch (Exception e) {
                logHelper.saveFail(name, e);
            }
        } else {
            logHelper.saveDuplicateFail(name);
        }

    }
}

