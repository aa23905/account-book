package com.example.accountbook.config;

import com.example.accountbook.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. REST API 不需要 CSRF
                .csrf(csrf -> csrf.disable())

                // 2. 无状态 — 不创建也不使用 Session
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. 接口权限控制
                .authorizeHttpRequests(auth -> auth
                        // 注册 / 登录接口放行（不需要 token）
                        .requestMatchers("/api/auth/**").permitAll()
                        // 其他所有请求必须经过认证
                        .anyRequest().authenticated()
                )

                // 4. 把 JWT 过滤器加在 Spring Security 默认的 UsernamePasswordAuthenticationFilter 之前
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt 加密 — 面试常问：密码为什么要用 BCrypt 而不是 MD5？
        return new BCryptPasswordEncoder();
    }
}