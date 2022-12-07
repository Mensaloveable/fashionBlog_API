package com.loveable.fashionblogapi.dto;

import com.loveable.fashionblogapi.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponseDto {
    private String post;
    private Date createdAt;
    private String category;
    private User user;
}
