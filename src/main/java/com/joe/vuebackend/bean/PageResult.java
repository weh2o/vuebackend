package com.joe.vuebackend.bean;

import lombok.Data;

import java.util.List;

/**
 * 返回給前端的分頁結果
 * @param <t>
 */
@Data
public class PageResult<t> {

    /**
     * 資料總數
     */
    private Long total;

    /**
     * 資料
     */
    private List<t> data;
}
