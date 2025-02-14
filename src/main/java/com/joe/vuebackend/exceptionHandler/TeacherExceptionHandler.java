package com.joe.vuebackend.exceptionHandler;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.exception.teacherNo.TeacherNoDuplicateException;
import com.joe.vuebackend.exception.teacherNo.TeacherNoIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 教師異常處理器
 */
@RestControllerAdvice
@Slf4j
public class TeacherExceptionHandler {

    @ExceptionHandler(TeacherNoIsNullException.class)
    public HttpResult<String> teacherNoIsNull(TeacherNoIsNullException ex) {
        return HttpResult.fail("教師證編號不能沒有輸入");
    }

    @ExceptionHandler(TeacherNoDuplicateException.class)
    public HttpResult<String> teacherNoDuplicate(TeacherNoDuplicateException ex) {
        return HttpResult.fail("教師證編號: " + ex.getMessage() + "已經存在");
    }
}
