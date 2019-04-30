/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 9:53:12 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.securities;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.edu.fa.springmvcsmarthome.dto.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException, ServletException {
    // TODO Auto-generated method stub
    Authentication auth = SecurityContextHolder.getContext()
        .getAuthentication();
    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied!");
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, new Date(),
        "Access Denied!");
    ObjectMapper obj = new ObjectMapper();
    String json = obj.writeValueAsString(apiError);
    response.getWriter().write(json);
  }

}
