package com.joe.vuebackend.constant;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CourseLocationType {

    /*

     */

    LIBRARY("1","LIBRARY", "圖書館"),
    PLAYGROUND("2", "PLAYGROUND", "操場"),
    GYMNASIUM("3","GYMNASIUM", "體育館"),
    CLASSROOM_101("4","CLASSROOM_101","101教室");


    /**
     * 編號
     */
    @NonNull
    @Getter
    private final String code;

    /**
     * 英文名稱
     */
    @NonNull
    @Getter
    private final String name;

    /**
     * 中文名稱
     */
    @NonNull
    @Getter
    private final String nameZh;
}
