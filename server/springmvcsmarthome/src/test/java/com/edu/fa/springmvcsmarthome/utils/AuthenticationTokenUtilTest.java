/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 1, 2019 3:43:18 PM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.nimbusds.jwt.JWTClaimsSet;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationTokenUtilTest {
  @InjectMocks
  private AuthenticationTokenUtil authenticationTokenUtil;

  private String token = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NDM2NDgzOTksInVzZXJuYW1lIjoiYWRta"
      + "W4ifQ.aTttSa1-rFdU11E7IAiR9GPOYHlPpZ1Ue5wSAMSgEqM";

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetExpirationDateFromToken() {
    Date date = authenticationTokenUtil.getExpirationDateFromToken(token);
    assertNotNull("Not null", date);

    assertTrue("True", date.after(new Date()));
  }

  @Test
  public void testIsTokenExpired() {
    boolean flag = authenticationTokenUtil.isTokenExpired(token);
    assertEquals("True", false, flag);

    assertFalse("False", flag);
  }

  @Test
  public void testGenerateShareSecret() {
    byte[] sharedSecret = authenticationTokenUtil.generateShareSecret();
    byte one = sharedSecret[0];
    assertEquals("True", 49, one);
    assertEquals("True", 32, sharedSecret.length);
  }

  @Test
  public void testGetClaimsFromToken() throws ParseException {
    JWTClaimsSet jwtClaimsSet = authenticationTokenUtil
        .getClaimsFromToken(token);
    String username = jwtClaimsSet.getStringClaim("username");
    assertEquals("True", "admin", username);
  }

  public void testGetClaimsFromTokenFalse() {

    JWTClaimsSet jwtClaimsSet = authenticationTokenUtil
        .getClaimsFromToken("1234");
    assertNull("Null", jwtClaimsSet);
  }

  @Test
  public void testGenerateExpirationDateForWemosD1R2() {
    Date date = new Date(System.currentTimeMillis());
    Date test = authenticationTokenUtil.generateExpirationDateForWemosD1R2();
    boolean flag = date.before(test);
    assertEquals("True", true, flag);
  }

  @Test
  public void testGenerateExpirationDate() {
    Date date = authenticationTokenUtil.generateExpirationDate();
    Date test = new Date(System.currentTimeMillis() + Constants.EXPIRE_TIME);
    boolean flag = date.before(test);
    assertEquals("true", false, flag);
  }
}
