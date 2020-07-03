package com.example.omdb.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class MoviesControllerAdvice extends ResponseEntityExceptionHandler
{
	@Override
	protected ResponseEntity <Object> handleExceptionInternal (
		Exception ex,
		Object body,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request)
	{
		log.warn ("", ex.fillInStackTrace ());
		
		return new ResponseEntity <> (HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
