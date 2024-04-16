package com.joe.vuebackend.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.utils.JwtUtil;
import com.joe.vuebackend.vo.UserInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JwtToken驗證過濾器
 */
@Component
public class JwtVerifyFilter extends OncePerRequestFilter {

    @Setter(onMethod_ = @Autowired)
    private JwtUtil jwtUtil;

    @Setter(onMethod_ = @Autowired)
    private ObjectMapper objectMapper;

    /**
     * 自定義驗證過濾
     *
     * @param request     請求
     * @param response    響應
     * @param filterChain 執行鍊
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1.放行 登入、註冊 請求
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/login") || requestURI.equals("register")) {
            doFilter(request, response, filterChain);
            return;
        }

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=utf-8");
        HttpResult<Object> fail = HttpResult.fail(HttpStatus.UNAUTHORIZED.value(), "請重新登入");
        String failInfo = objectMapper.writeValueAsString(fail);


        // 2.其他請求必須判斷是否有Jwt Token
        String jwtToken = request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) {
            PrintWriter writer = response.getWriter();
            writer.println(failInfo);
            return;
        }

        // 3.驗證jwt
        boolean verifyResult = jwtUtil.verifyToken(jwtToken);
        // 3.1 驗證失敗，返回結果給前端並終止方法
        if (!verifyResult) {
            PrintWriter writer = response.getWriter();
            writer.println(failInfo);
            return;
        }

        // 4.驗證成功的處理
        String userInfoStr = jwtUtil.getUserInfo(jwtToken);
        // 將JSON格式的「用戶資料」轉換回物件
        UserInfo userInfo = objectMapper.readValue(userInfoStr, UserInfo.class);
        User user = UserInfo.ofUser(userInfo);
        // 用戶權限
        Set<SimpleGrantedAuthority> userAuthSet = userInfo.getAuthorities().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        // 將 Token 放入 Security中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, userAuthSet);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        doFilter(request, response, filterChain);
    }
}
