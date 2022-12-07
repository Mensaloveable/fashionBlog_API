package com.loveable.fashionblogapi.controllers;

import com.loveable.fashionblogapi.dto.PostCreationDto;
import com.loveable.fashionblogapi.dto.PostResponseDto;
import com.loveable.fashionblogapi.services.PostService;
import com.loveable.fashionblogapi.util.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String get(){
        return "Welcome here";
    }

    @PostMapping("/create_post")
    public ApiResponse<PostResponseDto> createPost(@RequestBody PostCreationDto postCreationDto, HttpSession httpSession) {
        return postService.createPost(postCreationDto, httpSession);
    }
}
