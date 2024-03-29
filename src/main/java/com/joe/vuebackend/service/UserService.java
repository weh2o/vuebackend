package com.joe.vuebackend.service;

import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.vo.StudentVo;

import java.util.Optional;


public interface UserService {

    /**
     * 登入
     * @param user
     * @return
     */
    Optional<User> login(User user);

}
