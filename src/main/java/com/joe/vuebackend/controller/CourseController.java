package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.CourseInfo;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.repository.condition.CourseCondition;
import com.joe.vuebackend.service.CourseService;
import com.joe.vuebackend.vo.CourseVo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {

    @Setter(onMethod_ = @Autowired)
    private CourseService courseService;

    @GetMapping
    public HttpResult<PageResult<CourseVo>> getAll(CourseCondition condition) {
        PageResult<CourseVo> page = courseService.findAllPage(condition);
        return HttpResult.success("資料獲取成功", page);
    }

    @PostMapping
    public HttpResult<String> save(@RequestBody CourseInfo info) {
        return courseService.save(info);
    }

}
