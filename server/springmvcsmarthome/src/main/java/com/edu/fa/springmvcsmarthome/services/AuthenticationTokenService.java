/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 9:06:09 PM
 * @version 1.0
 */
/**
 *
 */

package com.edu.fa.springmvcsmarthome.services;

import java.text.ParseException;

/**
 * @author linkx
 *
 */
public interface AuthenticationTokenService {
  public abstract String generateTokenLogin(String username);

  public abstract boolean validateTokenLogin(String token)
      throws ParseException;

  public abstract String getUsernameFromToken(String token)
      throws ParseException;
}
