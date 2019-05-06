/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 3, 2019 3:54:41 PM
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
public class UserAccountDto implements Serializable {
  private static final long serialVersionUID = -2667362186036735218L;
  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserAccountDto(String username, String password) {
    super();
    this.username = username;
    this.password = password;
  }

  public UserAccountDto() {
    super();
  }

}
