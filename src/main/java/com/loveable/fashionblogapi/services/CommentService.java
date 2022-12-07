package com.loveable.fashionblogapi.services;

import com.loveable.fashionblogapi.dto.CommentCreationDto;
import com.loveable.fashionblogapi.util.ApiResponse;
import jakarta.servlet.http.HttpSession;

public interface CommentService {

    ApiResponse createComment(CommentCreationDto commentCreationDto, Long postId, HttpSession httpSession);
}
