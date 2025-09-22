package com.yxchai.javaassessment.exception;

import com.yxchai.javaassessment.constant.CustomerStatus;
import com.yxchai.javaassessment.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomerServiceExceptionHandler {

    @ExceptionHandler(CustomerServiceException.class)
    public ResponseEntity<ResponseDto> CustomerExceptionHandler(CustomerServiceException customerServiceException){
        ResponseDto responseDto = new ResponseDto(CustomerStatus.FAILED.getText(), customerServiceException.getMessage());

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerServiceCustomException.class)
    public ResponseEntity<ResponseDto> CustomerCustomExceptionHandler(CustomerServiceCustomException customerServiceCustomException){
        ResponseDto responseDto = new ResponseDto(CustomerStatus.FAILED.getText(), customerServiceCustomException.getMessage());

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException){
        List<String> errorMessage = new ArrayList<>();

        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(error -> {
           errorMessage.add(error.getDefaultMessage());
        });

        ResponseDto responseDto = new ResponseDto(CustomerStatus.FAILED.getText(), errorMessage.toString());

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
