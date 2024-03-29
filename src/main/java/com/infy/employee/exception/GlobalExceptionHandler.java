package com.infy.employee.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalException.class)
    public final ResponseEntity<ExceptionResponse> handlerGlobalException(GlobalException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorMessage(ex.getMessage());
        response.setErrorCode(ex.getHttpStatus().value());
        response.setErrorType(ex.getHttpStatus().name());
        response.setErrorTime(System.currentTimeMillis());
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }
}
