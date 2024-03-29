package com.joe.vuebackend.controller;

import com.joe.vuebackend.domain.Student;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.service.StudentService;
import com.joe.vuebackend.vo.HttpResult;
import com.joe.vuebackend.vo.StudentVo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public HttpResult<User> addStudent(@RequestBody StudentVo vo) {
        HttpResult<User> result = new HttpResult<>();
        Optional<Student> optional = studentService.addStudent(vo);
        if (optional.isPresent()) {
            User target = optional.get();
            result = HttpResult.success("新增成功", target);
        } else {
            result.setCode(HttpStatus.BAD_REQUEST.value());
            result.setMsg("新增失敗");
        }
        return result;
    }

    @GetMapping("/all")
    public HttpResult<List<StudentVo>> getAllVo() {
        List<StudentVo> list = studentService.findAllVo();
        return HttpResult.success("你好", list);
    }

    @GetMapping
    public HttpResult<StudentVo> findOne() {
        HttpResult<StudentVo> result = new HttpResult<>();
        return result;
    }
}
