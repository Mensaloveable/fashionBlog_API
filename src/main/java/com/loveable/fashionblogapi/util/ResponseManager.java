package com.loveable.fashionblogapi.util;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ResponseManager<T> {
    public ApiResponse<T> success(T data, HttpStatus status){
        return new ApiResponse<> (status.value(),"Operation Successful", true, data, status);
    }
    public ApiResponse<T> error(String errorMessage, HttpStatus status) {
        return new ApiResponse<>(status.value(), errorMessage, false, null, status);
    }
}
