package com.joe.vuebackend.exceptionHandler;

import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.utils.BindingResultHelper;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局異常處理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResult<String> validExceptionCatch(MethodArgumentNotValidException ex) {
        String failMsg = BindingResultHelper.failMsg(ex.getBindingResult());
        return HttpResult.fail(failMsg);
    }
}
