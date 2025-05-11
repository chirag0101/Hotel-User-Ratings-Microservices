package com.hotel.service.HotelService.payload;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public class ApiResponse {
    private Object message;
    private Boolean isSuccess;
    private HttpStatus httpStatus;

    public ApiResponse(){}

    public ApiResponse(Object message, Boolean isSuccess, HttpStatus httpStatus) {
        this.message = message;
        this.isSuccess = isSuccess;
        this.httpStatus = httpStatus;
    }

    //builder method to set values for response
//    public static <T> ApiResponse<T> builder(String message, boolean isSuccess, HttpStatus status) {
//        return new ApiResponse<>(message, isSuccess, status);
//    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}