package com.joe.vuebackend.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class JwtInterceptor implements HandlerInterceptor {

    @Setter(onMethod_ = @Autowired)
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String method = request.getMethod();
        if (HttpMethod.OPTIONS.toString().equals(method)) {
            return true;
        }

        // 驗證token
        String token = request.getHeader("token");

        HttpResult<String> result = JwtUtil.checkSign(token);
        if (HttpStatus.OK.value() == result.getCode()) {
            return true;
        }

        // 沒token，代表沒登入回傳401給前端
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=utf-8");
        HttpResult<Object> fail = HttpResult.fail(HttpStatus.UNAUTHORIZED.value(), "請重新登入");
        String failInfo = objectMapper.writeValueAsString(fail);
        PrintWriter writer = response.getWriter();
        writer.println(failInfo);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
