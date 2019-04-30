/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 30, 2019 10:38:37 AM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.edu.fa.springmvcsmarthome.customexception.UnauthorisedException;
import com.edu.fa.springmvcsmarthome.dto.ApiError;

/**
 * 
 * @author linkx
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  private static final Logger LOGGER = LoggerFactory
      .getLogger(CustomExceptionHandler.class);

  @ExceptionHandler(UnauthorisedException.class)
  protected ResponseEntity<Object> handleEntityNotFound(
      UnauthorisedException ex) {
    ApiError apiError = new ApiError(HttpStatus.ALREADY_REPORTED);
    return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return new ResponseEntity<>(apiError, apiError.getHttpStatus());
  }
}
