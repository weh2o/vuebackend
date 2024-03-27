package com.joe.vuebackend.constant;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoleType {
    ADMIN("1", "ADMIN", "系統管理者"),

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

