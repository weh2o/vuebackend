package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.repository.condition.TeacherCondition;
import com.joe.vuebackend.service.TeacherService;
import com.joe.vuebackend.vo.TeacherVo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 老師
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Setter(onMethod_ = @Autowired)
    private TeacherService teacherService;

    @GetMapping
    public HttpResult<List<TeacherVo>> getAll(TeacherCondition condition) {
        return teacherService.findAll(condition);
    }
}
