/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 10:21:17 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edu.fa.springmvcsmarthome.entities.UserAccount;
import com.edu.fa.springmvcsmarthome.services.AuthenticationToken;
import com.edu.fa.springmvcsmarthome.services.UserAccountService;

@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {
  @Autowired
  private UserAccountService userAccountService;
  @Autowired
  private AuthenticationToken authenticationToken;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseEntity<String> login(HttpServletRequest request,
      @RequestBody UserAccount userAccount) {
    String result = "";
    HttpStatus httpStatus = null;
    Optional<UserAccount> optional = userAccountService.findByUsername(userAccount.getUsername());
    if (optional.isPresent()) {
      UserAccount account = optional.get();
      result = authenticationToken.generateTokenLogin(account.getUsername());
      httpStatus = HttpStatus.OK;
    } else {
      result = "Wrong username and password";
      httpStatus = HttpStatus.BAD_REQUEST;
    }
    return new ResponseEntity<>(result, httpStatus);
  }

  @GetMapping(value = "/hello")
  public String welcome() {
    return "Hello";
  }
}
