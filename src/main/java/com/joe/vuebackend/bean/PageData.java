package com.joe.vuebackend.bean;

import lombok.Data;

@Data
public class PageData {

    /**
     * 當前頁數
     */
    private Integer page;

    /**
     * 資料展示的數量
     */
    private Integer pageSize;

    /**
     * 排序屬性
     */
    private String prop;

    /**
     * 排序規則
     */
    private String order;
}
