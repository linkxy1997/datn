/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 30, 2019 10:42:21 AM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.customexception;

/**
 * 
 * @author linkx
 *
 */
public class UnauthorisedException extends Exception {

  private static final long serialVersionUID = 1979215988302946407L;

  public UnauthorisedException(String errorMessage) {
    super(errorMessage);
  }
}
