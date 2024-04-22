package com.joe.vuebackend.config;

import com.joe.vuebackend.constant.URLConstant;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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
                        .requestMatchers(URLConstant.LOGOUT).permitAll()
                        .requestMatchers(URLConstant.LOGIN).permitAll()
                        .requestMatchers(URLConstant.REGISTER).permitAll()
                        .anyRequest().authenticated()
        );

        // 添加過濾器。將「驗證JwtToken過濾器」在「驗證用戶帳號、密碼過濾器」之前添加
        http.addFilterBefore(jwtVerifyFilter, UsernamePasswordAuthenticationFilter.class);

        // 設置跨域
        http.cors(cors -> cors.configurationSource(configurationSource()));
        return http.build();
    }

    // 跨域配置物件
    private CorsConfigurationSource configurationSource() {
        // CorsConfiguration: 用於配置跨域處理的物件
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("*"));  // 允許的請求頭
        corsConfiguration.setAllowedMethods(List.of("*"));  // 允許的方法(get、post)
        corsConfiguration.setAllowedOrigins(List.of("*"));  // 允許的源（網域、域名）
        corsConfiguration.setMaxAge(3600L);                     // 最大存活時間
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 通過指定 "/**"，表示對所有請求進行跨域處理，並使用前面定義的 CorsConfiguration 進行配置。
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
