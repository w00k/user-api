package com.wook.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 8968346497011450325L;

	public BusinessException(String message) {
		super(message);
	}

}
