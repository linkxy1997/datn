/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 9:52:21 PM
 * @version 1.0
 */
/**
 *
 */

package com.edu.fa.springmvcsmarthome.securities;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author linkx
 *
 */

public final class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {
  
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    // TODO Auto-generated method stub
    System.out.println("vao");
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write("Unauthorized" + authException.getMessage());
  }

}
