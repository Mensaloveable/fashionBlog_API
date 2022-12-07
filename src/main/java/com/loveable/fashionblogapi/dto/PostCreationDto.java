package com.loveable.fashionblogapi.dto;

import com.loveable.fashionblogapi.entities.Comment;
import com.loveable.fashionblogapi.entities.PostLike;
import com.loveable.fashionblogapi.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class PostCreationDto {
    private String post;
    private Date createdAt;
    private String category;
    private User user;
}
