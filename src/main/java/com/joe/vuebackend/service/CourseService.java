package com.joe.vuebackend.service;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.repository.condition.CourseCondition;
import com.joe.vuebackend.vo.CourseVo;

public interface CourseService {

    HttpResult<PageResult<CourseVo>> findAllPage(CourseCondition condition);
}
