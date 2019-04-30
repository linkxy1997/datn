/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 30, 2019 11:52:17 AM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiError {
  private HttpStatus httpStatus;
  private Date timestamp;
  private String message;

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ApiError() {
    super();
  }

  public ApiError(HttpStatus httpStatus, Date timestamp, String message) {
    super();
    this.httpStatus = httpStatus;
    this.timestamp = timestamp;
    this.message = message;
  }

  public ApiError(HttpStatus httpStatus) {
    super();
    this.httpStatus = httpStatus;
  }
}
