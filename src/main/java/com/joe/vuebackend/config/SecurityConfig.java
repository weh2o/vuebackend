package com.joe.vuebackend.config;

import com.joe.vuebackend.filter.JwtVerifyFilter;
import com.joe.vuebackend.service.impl.UserDetailService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security配置
 */
@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Setter(onMethod_ = @Autowired)
    private UserDetailService userDetailService;

    @Setter(onMethod_ = @Autowired)
    private JwtVerifyFilter jwtVerifyFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 關閉csrf機制
        http.csrf(csrf -> csrf.disable());

        // 配置請求攔截方式
        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/register").permitAll()
                        .anyRequest().authenticated()
        );

        // 添加過濾器。將「驗證JwtToken過濾器」在「驗證用戶帳號、密碼過濾器」之前添加
        http.addFilterBefore(jwtVerifyFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
