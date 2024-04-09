package com.joe.vuebackend.repository.condition;

import lombok.Data;

@Data
public class BaseCondition {
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

    public Integer getPage() {
        if (page != 0){
            return page - 1;
        }
        return page;
    }
}
