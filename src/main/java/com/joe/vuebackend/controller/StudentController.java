package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.service.StudentService;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageData;
import com.joe.vuebackend.vo.StudentVo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {

    @Setter(onMethod_ = @Autowired)
    private StudentService studentService;

    @PostMapping
    public HttpResult<StudentVo> save(@RequestBody StudentVo vo) {
        HttpResult<StudentVo> result = HttpResult.fail();
        Optional<StudentVo> optional = studentService.saveReturnVo(vo);
        if (optional.isPresent()) {
            StudentVo target = optional.get();
            result = HttpResult.success(target);
        }
        return result;
    }

    @GetMapping("/all")
    public HttpResult<PageResult<StudentVo>> getAllVo(PageData pageData) {
        PageResult<StudentVo> result = studentService.findAllVo(pageData);
        return HttpResult.success("你好", result);
    }

    @GetMapping("/{id}")
    public HttpResult<StudentVo> findOne(@PathVariable("id") String id) {
        HttpResult<StudentVo> result = HttpResult.fail();
        Optional<StudentVo> optional = studentService.findOneVo(id);
        if (optional.isPresent()) {
            result = HttpResult.success(optional.get());
        }
        return result;
    }

    @DeleteMapping("{id}")
    public HttpResult<String> remove(@PathVariable("id") String id) {
        return studentService.remove(id);
    }

}
