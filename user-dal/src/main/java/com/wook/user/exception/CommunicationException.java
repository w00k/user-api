package com.wook.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class CommunicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1138218621335252192L;

	public CommunicationException(String message) {
		super(message);
	}

}
