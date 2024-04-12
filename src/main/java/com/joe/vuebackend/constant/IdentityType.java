package com.joe.vuebackend.constant;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 身分類型
 */
@RequiredArgsConstructor
public enum IdentityType {
    ADMIN("1", "ADMIN", "系統管理者"),

    TEACHER("2", "TEACHER", "老師"),

    STUDENT("3", "STUDENT", "學生"),

    VISITOR("99", "VISITOR", "遊客");

    @NonNull
    @Getter
    private final String code;

    /**
     * 角色英文(具體判斷角色用)
     */
    @NonNull
    @Getter
    private final String text;

    /**
     * 角色中文
     */
    @NonNull
    @Getter
    private final String textZh;
}