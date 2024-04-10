package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.service.CourseLocationService;
import com.joe.vuebackend.vo.CourseLocationVo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 地點
 */
@CrossOrigin
@RestController
@RequestMapping("/courseLocation")
public class CourseLocationController {

    @Setter(onMethod_ = @Autowired)
    private CourseLocationService courseLocationService;

    @GetMapping
    public HttpResult<List<CourseLocationVo>> getAll() {
        return courseLocationService.findAll();
    }

}
