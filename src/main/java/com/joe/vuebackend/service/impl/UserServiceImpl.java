package com.joe.vuebackend.service.impl;


import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.repository.UserRepository;
import com.joe.vuebackend.service.UserService;
import com.joe.vuebackend.utils.JwtUtil;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;

    @Override
    public String login(User user) {
        String token = "";

        Optional<User> dbResult = userRepository.findByName(user.getName());
        if (dbResult.isPresent()) {
            User dbUser = dbResult.get();
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8));
            // 登入成功
            if (dbUser.getPassword().equals(password)) {
                token = JwtUtil.sign(dbUser.getId());
            }
        }
        return token;
    }
}
