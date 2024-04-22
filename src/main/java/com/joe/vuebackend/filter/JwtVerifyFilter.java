package com.joe.vuebackend.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joe.vuebackend.bean.HttpResult;
import com.joe.vuebackend.constant.CacheConstant;
import com.joe.vuebackend.constant.TimeConstant;
import com.joe.vuebackend.domain.User;
import com.joe.vuebackend.utils.JwtUtil;
import com.joe.vuebackend.utils.RedisCacheUtil;
import com.joe.vuebackend.utils.URLHelper;
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
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    @Setter(onMethod_ = @Autowired)
    private RedisCacheUtil redisCacheUtil;

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

        // 1.放行 登入、註冊、登出 請求
        String requestURI = request.getRequestURI();
        List<String> excludeUrl = URLHelper.getExcludeUrl();
        if (excludeUrl.contains(requestURI)) {
            doFilter(request, response, filterChain);
            return;
        }

        // 2.其他請求必須判斷是否有Jwt Token
        String jwtToken = request.getHeader(CacheConstant.TOKEN);
        if (StringUtils.isEmpty(jwtToken)) {
            verifyFail(response);
            return;
        }

        // 3.從Redis中找對應的「使用者資料」
        String redisKey = String.format("%s%s", CacheConstant.LOGIN_TOKEN, jwtToken);
        UserInfo userInfo = redisCacheUtil.getCacheObject(redisKey);
        // 沒找到對應的，結束方法
        if (Objects.isNull(userInfo)) {
            verifyFail(response);
            return;
        }

        // 4.驗證成功的處理
        User user = UserInfo.ofUser(userInfo);

        // Redis使用者資料小於30分鐘時，刷新時間
        LocalDateTime expireTokenTime = user.getExpireTokenTime();
        LocalDateTime now = LocalDateTime.now();
        Duration between = Duration.between(now, expireTokenTime);
        long millis = between.toMillis();
        if (millis < TimeConstant.TIME_30_MINUTE) {
            redisCacheUtil.refreshExpireOneDay(redisKey, userInfo);
        }

        // 用戶權限
        Set<SimpleGrantedAuthority> userAuthSet = userInfo.getAuthorities().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        // 將 Token 放入 Security中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user, null, userAuthSet);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        doFilter(request, response, filterChain);
    }

    /**
     * 驗證失敗，打印錯誤訊息給前端
     *
     * @param response
     * @throws IOException
     */
    public void verifyFail(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json;charset=utf-8");
        HttpResult<Object> fail = HttpResult.fail(HttpStatus.UNAUTHORIZED.value(), "請重新登入");
        String failInfo = objectMapper.writeValueAsString(fail);
        PrintWriter writer = response.getWriter();
        writer.println(failInfo);
    }
}
