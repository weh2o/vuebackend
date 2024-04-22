package com.joe.vuebackend.service.impl;


import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.LoginInfo;
import com.joe.vuebackend.bean.RegisterInfo;
import com.joe.vuebackend.constant.CacheConstant;
import com.joe.vuebackend.constant.IdentityType;
import com.joe.vuebackend.domain.*;
import com.joe.vuebackend.repository.*;
import com.joe.vuebackend.service.MenuService;
import com.joe.vuebackend.service.StudentService;
import com.joe.vuebackend.service.TeacherService;
import com.joe.vuebackend.service.UserService;
import com.joe.vuebackend.utils.JwtUtil;
import com.joe.vuebackend.utils.RedisCacheUtil;
import com.joe.vuebackend.vo.MenuVo;
import com.joe.vuebackend.vo.UserInfo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Setter(onMethod_ = @Autowired)
    private AuthenticationManager authenticationManager;

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

    @Setter(onMethod_ = @Autowired)
    private StudentService studentService;

    @Setter(onMethod_ = @Autowired)
    private TeacherService teacherService;

    @Setter(onMethod_ = @Autowired)
    private PasswordEncoder passwordEncoder;

    @Setter(onMethod_ = @Autowired)
    private MenuService menuService;

    @Setter(onMethod_ = @Autowired)
    private RedisCacheUtil redisCacheUtil;

    @Override
    public HttpResult<UserInfo> login(LoginInfo info) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(info.getAccount(), info.getPassword());
            // 驗證
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            // 根據是否勾選「記住我」設置token過期時間
            int expireDay = 1;
            if (Objects.nonNull(info.getRememberMe())) {
                expireDay = 7;
            }
            // 使用者資料
            User user = (User) authenticate.getPrincipal();
            // 上次登入時間
            user.setLastLoginTime(LocalDateTime.now());
            // token過期時間
            user.setExpireTokenTime(LocalDateTime.now().plusDays(expireDay));
            userRepository.save(user);
            // 轉成換傳給前端的格式
            UserInfo userInfo = UserInfo.ofAll(user);
            UserInfo.setSpecial(userInfo, user);
            // 清單
            List<MenuVo> menuVos = menuService.getMenuVoByRole(user.getRolesName());
            userInfo.setMenus(menuVos);
            // 創建token
            String jwtToken = JwtUtil.createJwt(userInfo);
            userInfo.setToken(jwtToken);

            // 存入Redis
            redisCacheUtil.setCacheObject(CacheConstant.LOGIN_TOKEN + jwtToken, userInfo, expireDay, TimeUnit.DAYS);
            return HttpResult.success("登入成功", userInfo);
        } catch (AuthenticationException e) {
            log.error("登入失敗", e);
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
            student.setPassword(passwordEncoder.encode(info.getPassword()));
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
            target.setPassword(passwordEncoder.encode(info.getPassword()));
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

    public HttpResult<String> logout(String token) {
        String key = String.format("%s%s", CacheConstant.LOGIN_TOKEN, token);

        boolean result = redisCacheUtil.deleteObject(key);
        if (result) {
            return HttpResult.success("登出成功");
        }
        log.error("Redis的Token刪除失敗");
        return HttpResult.fail("出了點問題..");
    }

    @Override
    public HttpResult<UserInfo> getInfo(String token) {
        String id = JwtUtil.getUserId(token);
        if (StringUtils.isNotEmpty(id)) {
            Optional<User> optional = userRepository.findById(id);
            if (optional.isPresent()) {
                User dbUser = optional.get();
                UserInfo userInfo = UserInfo.ofAll(dbUser);
                UserInfo.setSpecial(userInfo, dbUser);
                return HttpResult.success(userInfo);
            }
        }
        return HttpResult.fail("取得使用者資料失敗");
    }

    @Override
    public HttpResult<String> updatePassword(String id, UserInfo pswInfo) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            String dbOldPass = user.getPassword();
            boolean matches = passwordEncoder.matches(pswInfo.getOldPassword(), dbOldPass);
            // 密碼一致，修改成新密碼
            if (matches) {
                String inputNewPass = passwordEncoder.encode(pswInfo.getNewPassword());
                user.setPassword(inputNewPass);
                userRepository.save(user);
                return HttpResult.success("密碼修改成功");
            }
        }
        return HttpResult.fail("密碼修改失敗，請確定原密碼是否正確");
    }

    @Override
    public HttpResult<String> updateInfo(String id, UserInfo userInfo) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            if (user instanceof Student student) {
                return studentService.updateInfo(student, userInfo);
            } else if (user instanceof Teacher teacher) {
                return teacherService.updateInfo(teacher, userInfo);
            } else {
                User newInfo = UserInfo.ofUser(userInfo);
                user.setName(newInfo.getName());
                user.setGender(newInfo.getGender());
                user.setBirth(newInfo.getBirth());
                user.setMail(newInfo.getMail());
                user.setPhone(newInfo.getPhone());
                user.setAddress(newInfo.getAddress());
                userRepository.save(user);
                return HttpResult.success("修改成功");
            }
        }
        return HttpResult.fail("修改失敗");
    }
}
