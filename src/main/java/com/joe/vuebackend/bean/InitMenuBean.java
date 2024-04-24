package com.joe.vuebackend.bean;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 初始化使用的菜單Bean
 */
@Data
@Builder
public class InitMenuBean {

    private String label;
    private String path;
    private String icon;
    private Integer sort;
    private List<String> roleNames;

    /**
     * 上層菜單
     */
    private String parent;
}
