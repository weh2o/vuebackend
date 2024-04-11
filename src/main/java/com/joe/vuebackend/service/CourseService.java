package com.joe.vuebackend.service;

import com.joe.vuebackend.bean.CourseInfo;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.repository.condition.CourseCondition;
import com.joe.vuebackend.vo.CourseVo;

public interface CourseService {

    PageResult<CourseVo> findAllPage(CourseCondition condition);

    /**
     * 添加課程
     *
     * @param info 課程資料
     * @return
     */
    HttpResult<String> save(CourseInfo info);

    HttpResult<String> remove(String id);

    HttpResult<String> update(CourseInfo info);
}
