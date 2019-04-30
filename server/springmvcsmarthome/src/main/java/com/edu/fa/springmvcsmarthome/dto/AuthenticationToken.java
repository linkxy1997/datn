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
  private String authenticationToken;

  public String getAuthenticationToken() {
    return authenticationToken;
  }

  public void setAuthenticationToken(String authenticationToken) {
    this.authenticationToken = authenticationToken;
  }

  public AuthenticationToken(String authenticationToken) {
    super();
    this.authenticationToken = authenticationToken;
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
    return "AuthenticationToken [authenticationToken=" + authenticationToken
        + "]";
  }

}
