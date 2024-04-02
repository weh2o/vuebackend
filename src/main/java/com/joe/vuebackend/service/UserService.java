package com.joe.vuebackend.service;

import com.joe.vuebackend.domain.User;


public interface UserService {

    /**
     * 登入
     *
     * @param user
     * @return
     */
    String login(User user);

}
