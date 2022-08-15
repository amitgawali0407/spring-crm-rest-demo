package com.org.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleexception(CustomerNotFoundException e){
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		error.setMessage(e.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<CustomerErrorResponse>(error, HttpStatus.NOT_FOUND);
		
	}
	

	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleAnyException(Exception e){
		
		CustomerErrorResponse error = new CustomerErrorResponse();
		
		error.setMessage(e.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<CustomerErrorResponse>(error, HttpStatus.BAD_REQUEST);
		
	}
}
