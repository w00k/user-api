package com.wook.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MessageException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -327128648689880523L;

	public MessageException(String message) {
		super(message);
	}
}
