package com.joe.vuebackend.service;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.vo.CourseLocationVo;

import java.util.List;

public interface CourseLocationService {

    HttpResult<List<CourseLocationVo>> findAll();
}
