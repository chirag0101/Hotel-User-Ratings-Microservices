package com.lcwd.user.service.UserService.exceptions;

import com.lcwd.user.service.UserService.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceHandlerException(ResourceNotFoundException e){
        return new ResponseEntity<>(ApiResponse.builder().message(e.getMessage()).isSuccess(false).httpStatus(HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<?> globalExceptionHandler(GlobalException e){
        return new ResponseEntity<>(ApiResponse.builder().message(e.getMessage()).isSuccess(false).httpStatus(HttpStatus.NOT_FOUND),HttpStatus.NOT_FOUND);
    }
}