package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.service.TeacherNoService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacherNo")
public class TeacherNoController {

    @Setter(onMethod_ = @Autowired)
    private TeacherNoService teacherNoService;

    @PostMapping
    public HttpResult<String> save(String no) {
        return teacherNoService.save(no);
    }
}
