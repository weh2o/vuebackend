package com.joe.vuebackend.service.impl;


import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.RegisterInfo;
import com.joe.vuebackend.constant.IdentityType;
import com.joe.vuebackend.domain.*;
import com.joe.vuebackend.repository.*;
import com.joe.vuebackend.service.UserService;
import com.joe.vuebackend.utils.JwtUtil;
import com.joe.vuebackend.vo.UserInfo;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;

    @Setter(onMethod_ = @Autowired)
    private StudentRepository stuRepository;

    @Setter(onMethod_ = @Autowired)
    private TeacherNoRepository teacherNoRepository;

    @Setter(onMethod_ = @Autowired)
    private TeacherRepository teacherRepository;

    @Setter(onMethod_ = @Autowired)
    private IdentityRepository identityRepository;

    @Override
    public HttpResult<UserInfo> login(User user) {
        UserInfo userInfo = new UserInfo();


        Optional<User> dbResult = userRepository.findByAccount(user.getAccount());
        if (dbResult.isPresent()) {
            User dbUser = dbResult.get();
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
            // 登入成功
            if (dbUser.getPassword().equals(password)) {
                String token = JwtUtil.sign(dbUser.getId());
                userInfo.setToken(token);
                userInfo.setName(dbUser.getName());
                // 上次登入時間
                dbUser.setLastLoginTime(LocalDateTime.now());
                userRepository.save(dbUser);
            }
        }

        if (StringUtils.isNotEmpty(userInfo.getToken())) {
            return HttpResult.success("登入成功", userInfo);
        } else {
            return HttpResult.fail(HttpStatus.UNAUTHORIZED.value(), "登入失敗，請再次確認使用者名稱與密碼");
        }
    }

    @Override
    public HttpResult<String> register(RegisterInfo info) {

        String identity = info.getIdentity();
        boolean isNew = true;
        // 學生
        if (IdentityType.STUDENT.getCode().equals(identity)) {
            Student student = new Student();

            // 以前就在資料庫裡的學生
            Optional<Student> dbStu = stuRepository.findByNo(info.getNo());
            if (dbStu.isPresent()) {
                if (StringUtils.isNotEmpty(dbStu.get().getAccount())) {
                    return HttpResult.fail("該學生證已經註冊");
                }
                student = dbStu.get();
                isNew = false;
            }
            student.setAccount(info.getAccount());
            String password = DigestUtils.md5DigestAsHex(info.getPassword().getBytes(StandardCharsets.UTF_8));
            student.setPassword(password);
            Student resultStu = stuRepository.save(student);

            // 新學生:
            if (isNew) {
                // 給予身分
                Optional<Identity> optional = identityRepository.findByName(IdentityType.STUDENT.getText());
                if (optional.isPresent()) {
                    Identity dbIdentity = optional.get();
                    dbIdentity.addUserList(resultStu);
                    resultStu.setIdentity(dbIdentity);
                    stuRepository.save(resultStu);
                }
                return HttpResult.success("註冊成功");
            }
            return HttpResult.success("註冊成功，資料已同步");
        }

        // 教師
        if (IdentityType.TEACHER.getCode().equals(identity)) {
            Optional<TeacherNo> optional = teacherNoRepository.findByNo(info.getNo());
            // 1.教師證不存在
            if (optional.isEmpty()) {
                return HttpResult.fail("註冊失敗，請再次確認教師證是否正確");
            }

            // 2.教師證存在
            Teacher target = new Teacher();
            TeacherNo teacherNo = optional.get();

            // 2.1 教師證已被使用，找使用該教師證的教師資料，確認是否有帳號屬性
            Optional<Teacher> dbOptional = teacherRepository.findByNo_No(teacherNo.getNo());
            if (dbOptional.isPresent()) {
                Teacher dbTeacher = dbOptional.get();
                if (StringUtils.isNotEmpty(dbTeacher.getAccount())) {
                    return HttpResult.fail("註冊失敗，此教師證已經註冊");
                }
                target = dbTeacher;
                isNew = false;
            }

            // 2.2 教師證還未被使用，判斷是否有效
            if (Boolean.TRUE == teacherNo.getDisable()) {
                return HttpResult.fail("註冊失敗，教師證已停用");
            }
            // 註冊操作
            target.setAccount(info.getAccount());
            String password = DigestUtils.md5DigestAsHex(info.getPassword().getBytes(StandardCharsets.UTF_8));
            target.setPassword(password);
            Teacher resultTeacher = teacherRepository.save(target);

            // 新的教師:
            if (isNew) {
                // 給予身分
                Optional<Identity> optionalIdentity = identityRepository.findByName(IdentityType.TEACHER.getText());
                if (optionalIdentity.isPresent()) {
                    Identity dbIdentity = optionalIdentity.get();
                    dbIdentity.addUserList(resultTeacher);
                    resultTeacher.setIdentity(dbIdentity);
                }
                // 放入教師證，將教師證改為使用中
                teacherNo.setAvailable(false);
                resultTeacher.setNo(teacherNo);
                teacherRepository.save(resultTeacher);
            }
            return HttpResult.success("註冊成功");
        }

        return HttpResult.fail("註冊失敗");
    }

    @Override
    public HttpResult<UserInfo> getInfo(String token) {
        String id = JwtUtil.getUserId(token);
        if (StringUtils.isNotEmpty(id)) {
            Optional<User> optional = userRepository.findById(id);
            if (optional.isPresent()) {
                UserInfo userInfo = UserInfo.ofAll(optional.get());
                return HttpResult.success(userInfo);
            }
        }
        return HttpResult.fail("取得使用者資料失敗");
    }
}
