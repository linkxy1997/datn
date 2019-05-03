/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 30, 2019 9:07:30 AM
 * @version 1.0
 */
/**
 * 
 */
package com.edu.fa.springmvcsmarthome.dto;

import java.io.Serializable;

/**
 * @author linkx
 *
 */
public class AuthenticationToken implements Serializable {

  private static final long serialVersionUID = 8161418860537050301L;
  private String authToken;

  public String getAuthenticationToken() {
    return authToken;
  }

  public void setAuthenticationToken(String authToken) {
    this.authToken = authToken;
  }

  public AuthenticationToken(String authToken) {
    super();
    this.authToken = authToken;
  }

  public AuthenticationToken() {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "AuthenticationToken [authToken=" + authToken + "]";
  }

}
