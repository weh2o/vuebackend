package com.joe.vuebackend.constant;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色權限類型
 */
@RequiredArgsConstructor
public enum RoleType {
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

    /**
     * 獲取所有角色權限英文名稱
     * @return
     */
    public static List<String> getAllText() {
        RoleType[] values = RoleType.values();
        ArrayList<String> target = new ArrayList<>();
        for (RoleType value : values) {
            target.add(value.getText());
        }
        return target;
    }
}

