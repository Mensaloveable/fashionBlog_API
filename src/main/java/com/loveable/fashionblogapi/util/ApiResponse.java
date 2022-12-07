package com.loveable.fashionblogapi.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse<T> {
    private int httpStatusCode;
    private String message;
    private Boolean success;
    private T data;
    private HttpStatus httpStatus;
    }

