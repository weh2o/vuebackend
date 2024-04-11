package com.joe.vuebackend.service;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.domain.Teacher;
import com.joe.vuebackend.repository.condition.TeacherCondition;
import com.joe.vuebackend.vo.TeacherVo;
import com.joe.vuebackend.vo.UserInfo;

import java.util.List;

public interface TeacherService {

    /**
     * 修改教師基本資料
     *
     * @param teacher  被修改的教師
     * @param userInfo 新的基本資料
     * @return
     */
    HttpResult<String> updateInfo(Teacher teacher, UserInfo userInfo);

    HttpResult<List<TeacherVo>> findAll(TeacherCondition condition);

}
