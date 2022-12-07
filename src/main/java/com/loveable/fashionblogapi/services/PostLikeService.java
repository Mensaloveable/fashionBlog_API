package com.loveable.fashionblogapi.services;

import com.loveable.fashionblogapi.util.ApiResponse;
import jakarta.servlet.http.HttpSession;

public interface PostLikeService {

    ApiResponse likePost(Long id, HttpSession httpSession);
}
