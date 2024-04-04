package com.joe.vuebackend.service.impl;


import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.RegisterInfo;
import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.repository.StudentRepository;
import com.joe.vuebackend.repository.UserRepository;
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
        Student student = new Student();
        Optional<Student> noStu = stuRepository.findByNo(info.getNo());
        if (noStu.isPresent()) {
            if (StringUtils.isNotEmpty(noStu.get().getAccount())) {
                return HttpResult.fail("該學生證已經註冊");
            }
            student = noStu.get();
        }
        student.setAccount(info.getAccount());
        String password = DigestUtils.md5DigestAsHex(info.getPassword().getBytes(StandardCharsets.UTF_8));
        student.setPassword(password);
        stuRepository.save(student);
        return HttpResult.success("註冊成功");
    }

    @Override
    public HttpResult<UserInfo> getInfo(String token) {
        String id = JwtUtil.getUserId(token);
        if (StringUtils.isNotEmpty(id)){
            Optional<User> optional = userRepository.findById(id);
            if (optional.isPresent()){
                UserInfo userInfo = UserInfo.ofAll(optional.get());
                return HttpResult.success(userInfo);
            }
        }
        return HttpResult.fail("取得使用者資料失敗");
    }
}
