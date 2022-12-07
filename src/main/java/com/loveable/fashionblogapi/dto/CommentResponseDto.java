package com.loveable.fashionblogapi.dto;

import com.loveable.fashionblogapi.entities.Post;
import com.loveable.fashionblogapi.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CommentResponseDto {

    private String comment;
    private Date createdAt;
    private User user;
    private Post post;
}
