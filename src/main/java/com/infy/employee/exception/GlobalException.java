package com.infy.employee.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@Data
public class GlobalException extends RuntimeException {

    private final String message;
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public GlobalException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public GlobalException(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}