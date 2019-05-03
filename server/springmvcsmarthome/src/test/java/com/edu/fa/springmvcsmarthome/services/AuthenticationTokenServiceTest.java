/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 2, 2019 9:26:53 AM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.edu.fa.springmvcsmarthome.services.impl.AuthenticationTokenServiceImpl;
import com.edu.fa.springmvcsmarthome.utils.AuthenticationTokenUtil;
import com.edu.fa.springmvcsmarthome.utils.Constants;
import com.nimbusds.jwt.JWTClaimsSet;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationTokenServiceTest {
  @InjectMocks
  private AuthenticationTokenServiceImpl authenticationTokenService;
  @Mock
  private AuthenticationTokenUtil authUtill;
  private String token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDM2NDgzOTksInVzZXJuYW1lIjoiYWRtaW4ifQ.a"
      + "TttSa1-rFdU11E7IAiR9GPOYHlPpZ1Ue5wSAMSgEqM";
  JWTClaimsSet jWTClaimsSet;
  JWTClaimsSet.Builder builder;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    builder = new JWTClaimsSet.Builder();
    builder.claim(Constants.USERNAME, "admin");
    jWTClaimsSet = builder.build();
  }

  @Test
  public final void testGenerateTokenLogin() {

  }

  @Test
  public final void testValidateTokenLogin() throws ParseException {
    when(authUtill.isTokenExpired(Mockito.anyString())).thenReturn(true);
    when(authUtill.getClaimsFromToken(Mockito.anyString()))
        .thenReturn(jWTClaimsSet);

    boolean flag = authenticationTokenService.validateTokenLogin(token);
    assertEquals("False", false, flag);
  }

  @Test
  public final void testGetUsernameFromToken()
      throws NullPointerException, ParseException {
    when(authUtill.getClaimsFromToken(Mockito.anyString()))
        .thenReturn(jWTClaimsSet);
    String username = authenticationTokenService.getUsernameFromToken(token);
    assertEquals("True", "admin", username);
  }

}
