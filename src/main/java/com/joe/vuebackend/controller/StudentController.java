package com.joe.vuebackend.controller;

import com.joe.vuebackend.bean.DeleteResult;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.bean.PageResult;
import com.joe.vuebackend.bean.StudentInfo;
import com.joe.vuebackend.repository.condition.StudentCondition;
import com.joe.vuebackend.service.StudentService;
import com.joe.vuebackend.validation.StudentValidation;
import com.joe.vuebackend.vo.StudentVo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 學生
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Setter(onMethod_ = @Autowired)
    private StudentService studentService;

    @Setter(onMethod_ = @Autowired)
    private StudentValidation studentValidation;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        if (Objects.nonNull(binder.getTarget()) &&
                binder.getTarget() instanceof StudentVo) {
            binder.addValidators(studentValidation);
        }
    }

    @PostMapping
    public HttpResult<StudentVo> save(@RequestBody @Validated StudentVo vo) {
        HttpResult<StudentVo> result = HttpResult.fail();

        Optional<StudentVo> optional = studentService.saveReturnVo(vo);

        if (optional.isPresent()) {
            StudentVo target = optional.get();
            result = HttpResult.success(target);
        }
        return result;
    }

    @GetMapping
    public HttpResult<PageResult<StudentVo>> getVo(StudentCondition condition) {
        PageResult<StudentVo> result = studentService.findAllVo(condition);
        return HttpResult.success("資料獲取成功", result);
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

    @DeleteMapping
    public HttpResult<DeleteResult<StudentInfo>> removeAll(@RequestBody List<StudentInfo> infos) {
        return studentService.removeAllById(infos);
    }


    @GetMapping("/search/{nameOrNo}")
    public HttpResult<PageResult<StudentVo>> search(StudentCondition condition) {
        PageResult<StudentVo> result = studentService.searchByNameOrNo(condition);
        return HttpResult.success("查詢成功", result);
    }
}
