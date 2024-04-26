package com.joe.vuebackend.bean;

import lombok.Data;

import java.io.Serializable;


@Data
public class StudentInfo implements Serializable {

    /**
     * 識別碼
     */
    private String id;

    /**
     * 姓名
     */
    private String name;
}
