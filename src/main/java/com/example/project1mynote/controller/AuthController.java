package com.example.project1mynote.controller;

import com.example.project1mynote.SecurityConfig.JwtUtil;
import com.example.project1mynote.dtoRequest.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Tạo token xác thực với username và password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        // Đặt Authentication vào SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Tạo JWT và trả về cho người dùng
        return jwtUtil.generateJwtToken(loginRequest.getUsername());
    }
}
