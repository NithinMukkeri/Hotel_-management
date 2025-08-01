package com.hotel.hotelManagement.exception;

import com.hotel.hotelManagement.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)

    public ResponseEntity<APIResponse> handleUserNotFound(UserNotFoundException ex) {
APIResponse response=new APIResponse();
response.setMessage(ex.getMessage());
response.setHttpStatus(HttpStatus.NOT_FOUND.value());
response.setData(null);
return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<APIResponse> badRequestException(BadRequestException ex){
        APIResponse response=new APIResponse();
        response.setMessage(ex.getMessage());
        response.setHttpStatus(HttpStatus.NOT_FOUND.value());
        response.setData(null);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
