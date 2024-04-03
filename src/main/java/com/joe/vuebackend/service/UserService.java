package com.joe.vuebackend.service;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.RegisterInfo;
import com.joe.vuebackend.domain.User;


public interface UserService {

    /**
     * 登入
     *
     * @param user
     * @return
     */
    HttpResult<String> login(User user);

    /**
     * 註冊
     *
     * @param info 使用者註冊資料
     * @return
     */
    HttpResult<String> register(RegisterInfo info);

}
