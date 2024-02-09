package com.infy.empolyee.exception;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ExceptionResponse {
	
	private int errorCode;
	private String errorType;
	private String errorMessage;
	private Long errorTime;

}
