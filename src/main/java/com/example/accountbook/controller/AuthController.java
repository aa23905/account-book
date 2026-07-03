package com.example.accountbook.controller;

import com.example.accountbook.dto.ApiResponse;
import com.example.accountbook.dto.LoginRequest;
import com.example.accountbook.dto.LoginResponse;
import com.example.accountbook.dto.RegisterRequest;
import com.example.accountbook.entity.User;
import com.example.accountbook.mapper.UserMapper;
import com.example.accountbook.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ApiResponse<LoginResponse> register(@Valid @RequestBody RegisterRequest request) {
        // 1. 检查用户名是否已存在
        if (userMapper.findByUsername(request.getUsername()) != null) {
            return ApiResponse.error(400, "用户名已存在");
        }

        // 2. 密码用 BCrypt 加密（绝对不能存明文！）
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());

        userMapper.insert(user);

        // 3. 注册成功自动登录，返回 JWT token
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());
        return ApiResponse.success(new LoginResponse(token, user.getUsername()));
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        // 1. 根据用户名查用户
        User user = userMapper.findByUsername(request.getUsername());
        if (user == null) {
            return ApiResponse.error(401, "用户名或密码错误");
        }

        // 2. 校验密码（BCrypt 自动处理加盐验证）
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ApiResponse.error(401, "用户名或密码错误");
        }

        // 3. 登录成功，生成 JWT 返回
        String token = jwtUtil.generateToken(user.getId(), user.getUsername());
        return ApiResponse.success(new LoginResponse(token, user.getUsername()));
    }
}