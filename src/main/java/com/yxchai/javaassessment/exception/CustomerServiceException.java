package com.yxchai.javaassessment.exception;

public class CustomerServiceException extends Exception{
    public CustomerServiceException() {
        super("Something went wrong. Please try again later !");
    }
}
