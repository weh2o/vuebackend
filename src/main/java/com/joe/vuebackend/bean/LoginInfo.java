package com.joe.vuebackend.bean;

import lombok.Data;

/**
 * 前端登入時輸入的資料
 */
@Data
public class LoginInfo {

    /**
     * 帳號
     */
    private String account;

    /**
     * 密碼
     */
    private String password;

    /**
     * 身分
     */
    private String identity;
}