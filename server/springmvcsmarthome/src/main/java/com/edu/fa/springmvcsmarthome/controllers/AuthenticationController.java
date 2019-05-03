/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 10:21:17 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edu.fa.springmvcsmarthome.dto.AuthenticationToken;
import com.edu.fa.springmvcsmarthome.entities.UserAccount;
import com.edu.fa.springmvcsmarthome.services.AuthenticationTokenService;
import com.edu.fa.springmvcsmarthome.services.UserAccountService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {
  @Autowired
  private UserAccountService userAccountService;
  @Autowired
  private AuthenticationTokenService authenticationTokenService;

  @PostMapping(value = "/login")
  public ResponseEntity<AuthenticationToken> login(HttpServletRequest request,
      @RequestBody UserAccount userAccount) {
    String result;
    HttpStatus httpStatus;
    boolean flag = userAccountService.findByUsername(userAccount);
    if (flag) {
      result = authenticationTokenService
          .generateTokenLogin(userAccount.getUsername());
      httpStatus = HttpStatus.OK;
    } else {
      result = "Wrong username and password";
      httpStatus = HttpStatus.BAD_REQUEST;
    }
    AuthenticationToken authenticationToken = new AuthenticationToken(result);
    return new ResponseEntity<>(authenticationToken, httpStatus);
  }

  @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> welcome() {
    return new ResponseEntity<>("Welcome", HttpStatus.OK);
  }
}
