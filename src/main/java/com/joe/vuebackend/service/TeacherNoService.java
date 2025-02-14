package com.joe.vuebackend.service;

import com.joe.vuebackend.bean.HttpResult;

public interface TeacherNoService {

    /**
     * 保存教師證
     *
     * @param no 教師證編號
     * @return
     */
    HttpResult<String> save(String no);
}
