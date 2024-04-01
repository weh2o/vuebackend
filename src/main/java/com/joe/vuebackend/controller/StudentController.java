package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.repository.condition.StudentCondition;
import com.joe.vuebackend.service.StudentService;
import com.joe.vuebackend.vo.StudentVo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public HttpResult<PageResult<StudentVo>> getAllVo(StudentCondition condition) {
        PageResult<StudentVo> result = studentService.findAllVo(condition);
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


    @GetMapping("/search/{nameOrNo}")
    public HttpResult<PageResult<StudentVo>> search(StudentCondition condition) {
        PageResult<StudentVo> result = studentService.searchByNameOrNo(condition);
        return HttpResult.success("查詢成功", result);
    }
}
