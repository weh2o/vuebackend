package com.joe.vuebackend.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {

    // 男生
    BOY("1", "男"),
    // 女生
    GIRL("2", "女"),
    // 未知
    UNKNOWN("3", "未知");

    @Getter
    private final String code;

    @Getter
    private final String text;
}