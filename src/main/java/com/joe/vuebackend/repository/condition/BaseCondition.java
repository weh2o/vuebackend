package com.joe.vuebackend.repository.condition;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseCondition implements Serializable {
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

    /**
     * 使用者識別碼
     */
    private String userId;

    public Integer getPage() {
        if (page != 0){
            return page - 1;
        }
        return page;
    }
}
