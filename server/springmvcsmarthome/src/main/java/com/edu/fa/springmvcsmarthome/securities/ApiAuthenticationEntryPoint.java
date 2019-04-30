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
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.edu.fa.springmvcsmarthome.dto.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author linkx
 *
 */

public final class ApiAuthenticationEntryPoint
    implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {
    // TODO Auto-generated method stub
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, new Date(),
        "Unauthorized");
    ObjectMapper obj = new ObjectMapper();
    String json = obj.writeValueAsString(apiError);
    response.getWriter().write(json);
  }

}
