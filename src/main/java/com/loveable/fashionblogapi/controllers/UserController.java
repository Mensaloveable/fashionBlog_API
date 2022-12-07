package com.loveable.fashionblogapi.controllers;

import com.loveable.fashionblogapi.dto.UserResponseDto;
import com.loveable.fashionblogapi.dto.UserSignUpDto;
import com.loveable.fashionblogapi.services.UserService;
import com.loveable.fashionblogapi.util.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String get() {
        return "Welcome, you can login or continue as anonymous";
    }

    @PostMapping("/signup")
    public ApiResponse<UserResponseDto> signUp(@RequestBody UserSignUpDto signUpDto) {
        return userService.signUp(signUpDto);
    }

    @GetMapping("/login")
    public ApiResponse<UserResponseDto> login(@RequestBody UserSignUpDto signUpDto, HttpSession httpSession) {
        return userService.login(signUpDto, httpSession);
    }

    @GetMapping("/logout")
    public ApiResponse<String> logout(HttpSession httpSession) {
        return userService.logout(httpSession);
    }

    @GetMapping("/all_users")
    public ApiResponse<List<UserResponseDto>> viewAllUsers(HttpSession httpSession) {
        return userService.getAllUsers(httpSession);
    }
}
