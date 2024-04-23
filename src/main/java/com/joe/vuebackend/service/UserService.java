package com.joe.vuebackend.service;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.LoginInfo;
import com.joe.vuebackend.bean.RegisterInfo;
import com.joe.vuebackend.vo.UserInfo;


public interface UserService {

    /**
     * 登入
     *
     * @param info
     * @return
     */
    HttpResult<UserInfo> login(LoginInfo info);

    /**
     * 註冊
     *
     * @param info 使用者註冊資料
     * @return
     */
    HttpResult<String> register(RegisterInfo info);

    /**
     * 獲取使用者資料
     *
     * @return
     */
    HttpResult<UserInfo> getInfo();

    /**
     * 修改使用者密碼
     *
     * @param id      使用者識別碼
     * @param pswInfo 使用者密碼資料
     * @return
     */
    HttpResult<String> updatePassword(String id, UserInfo pswInfo);

    /**
     * 修改使用者基本資料
     *
     * @param id       使用者識別碼
     * @param userInfo 新的使用者基本資料
     * @return
     */
    HttpResult<String> updateInfo(String id, UserInfo userInfo);

    /**
     * 登出
     *
     * @param token
     * @return
     */
    HttpResult<String> logout(String token);
}
