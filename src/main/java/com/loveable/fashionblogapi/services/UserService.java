package com.loveable.fashionblogapi.services;

import com.loveable.fashionblogapi.dto.UserResponseDto;
import com.loveable.fashionblogapi.dto.UserSignUpDto;
import com.loveable.fashionblogapi.util.ApiResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UserService {
    ApiResponse<UserResponseDto> signUp(UserSignUpDto signUpDto);

    ApiResponse<UserResponseDto> login(UserSignUpDto signUpDto, HttpSession httpSession);

    ApiResponse<String> logout(HttpSession session);

    ApiResponse<List<UserResponseDto>> getAllUsers(HttpSession httpSession);
}
