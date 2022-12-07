package com.loveable.fashionblogapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String uuid;
}
