package com.wook.user.exception.interceptor;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wook.user.exception.BusinessException;
import com.wook.user.exception.CommunicationException;
import com.wook.user.exception.MessageException;
import com.wook.user.exception.ObjectNotFoundException;
import com.google.gson.Gson;

@ControllerAdvice
@RestController
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public final ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException ex, WebRequest request) {
		UserExceptionResponse exceptionResponse = new UserExceptionResponse(new Date(),HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
				ex.getMessage(), request.getDescription(false));
		UserExceptionResponse result = null;
		try {
			result = new Gson().fromJson(ex.getMessage(), UserExceptionResponse.class) ;
		} catch (Exception e) {
			result= null;
		}
		return new ResponseEntity<Object>(result==null?exceptionResponse:result, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
		UserExceptionResponse exceptionResponse = new UserExceptionResponse(new Date(),HttpStatus.UNPROCESSABLE_ENTITY.value(), 
				HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), ex.getMessage(), request.getDescription(false));
		UserExceptionResponse result = null;
		try {
			result = new Gson().fromJson(ex.getMessage(), UserExceptionResponse.class) ;
		} catch (Exception e) {
			result= null;
		}
		return new ResponseEntity<Object>(result==null?exceptionResponse:result, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(MessageException.class)
	public final ResponseEntity<Object> handleMessageException(MessageException ex, WebRequest request) {
		UserExceptionResponse exceptionResponse = new UserExceptionResponse(new Date(),HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
				ex.getMessage(), request.getDescription(false));
		
		UserExceptionResponse result = null;
		try {
			result = new Gson().fromJson(ex.getMessage(), UserExceptionResponse.class) ;
		} catch (Exception e) {
			result= null;
		}
		
		return new ResponseEntity<Object>(result==null?exceptionResponse:result, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CommunicationException.class)
	public final ResponseEntity<Object> handleMessageException(CommunicationException ex, WebRequest request) {
		UserExceptionResponse exceptionResponse = new UserExceptionResponse(new Date(),HttpStatus.BAD_GATEWAY.value(), HttpStatus.BAD_GATEWAY.getReasonPhrase(),
				ex.getMessage(), request.getDescription(false));
		
		UserExceptionResponse result = null;
		try {
			result = new Gson().fromJson(ex.getMessage(), UserExceptionResponse.class) ;
		} catch (Exception e) {
			result= null;
		}
		
		return new ResponseEntity<Object>(result==null?exceptionResponse:result, HttpStatus.BAD_GATEWAY);
	}

}
