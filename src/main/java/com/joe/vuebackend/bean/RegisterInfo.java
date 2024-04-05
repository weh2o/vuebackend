package com.joe.vuebackend.bean;

import lombok.Data;

/**
 * 接收前端註冊的資料
 */
@Data
public class RegisterInfo {

    /**
     * 使用者名稱
     */
    private String name;

    /**
     * 帳號
     */
    private String account;

    /**
     * 密碼
     */
    private String password;

    /**
     * 學生證 or 教師證
     */
    private String no;


    /**
     * 身分
     */
    private String identity;

}