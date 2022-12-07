package com.loveable.fashionblogapi.controllers;

import com.loveable.fashionblogapi.services.PostLikeService;
import com.loveable.fashionblogapi.util.ApiResponse;
import com.loveable.fashionblogapi.entities.PostLike;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/like")
public class LikeController {

    private final PostLikeService postLikeService;

    @GetMapping("/like_post/{id}")
    public ApiResponse<PostLike> get(@PathVariable("id") Long commentId, HttpSession httpSession){
        return postLikeService.likePost(commentId, httpSession);
    }
}
