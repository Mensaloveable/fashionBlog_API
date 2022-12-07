package com.loveable.fashionblogapi.services;

import com.loveable.fashionblogapi.dto.PostCreationDto;
import com.loveable.fashionblogapi.dto.PostResponseDto;
import com.loveable.fashionblogapi.util.ApiResponse;
import jakarta.servlet.http.HttpSession;

public interface PostService {

    ApiResponse<PostResponseDto> createPost(PostCreationDto postCreationDto, HttpSession httpSession);
}
