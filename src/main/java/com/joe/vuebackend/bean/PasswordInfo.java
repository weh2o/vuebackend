package com.joe.vuebackend.bean;

import lombok.Data;

@Data
public class PasswordInfo {

    private String oldPassword;

    private String newPassword;
}