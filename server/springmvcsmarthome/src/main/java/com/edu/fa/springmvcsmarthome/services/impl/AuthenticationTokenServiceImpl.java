/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 9:08:22 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.services.impl;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.fa.springmvcsmarthome.services.AuthenticationTokenService;
import com.edu.fa.springmvcsmarthome.utils.AuthenticationTokenUtil;
import com.edu.fa.springmvcsmarthome.utils.Constants;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class AuthenticationTokenServiceImpl
    implements AuthenticationTokenService {
  @Autowired
  private AuthenticationTokenUtil authenticationTokenUtil;
  private static final Logger LOGGER = LoggerFactory
      .getLogger(AuthenticationTokenServiceImpl.class);

  @Override
  public String generateTokenLogin(String username) {
    // Auto-generated method stub
    String token = null;
    try {
      // Create HMAC signer
      JWSSigner signer = new MACSigner(
          authenticationTokenUtil.generateShareSecret());
      JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
      builder.claim(Constants.USERNAME, username);
      builder.expirationTime(authenticationTokenUtil.generateExpirationDate());
      JWTClaimsSet claimsSet = builder.build();

      JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
      SignedJWT signedJWT = new SignedJWT(jwsHeader, claimsSet);
      // Apply the HMAC protection
      signedJWT.sign(signer);
      // Serialize to compact form, produces something like
      // eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
      token = signedJWT.serialize();
    } catch (JOSEException e) {
      // Auto-generated catch block
      if (LOGGER.isErrorEnabled()) {
        LOGGER.error(e.getMessage(), e);
      }
    }
    return token;
  }

  @Override
  public boolean validateTokenLogin(String token) throws ParseException {
    // Auto-generated method stub
    if (token == null || token.trim().length() == 0) {
      return false;
    }
    String username = getUsernameFromToken(token);

    if (username == null || username.isEmpty()) {
      return false;
    }
    return !authenticationTokenUtil.isTokenExpired(token);
  }

  @Override
  public String getUsernameFromToken(String token) throws ParseException {
    // Auto-generated method stub
    JWTClaimsSet claims = authenticationTokenUtil.getClaimsFromToken(token);
    return claims.getStringClaim(Constants.USERNAME);
  }

}
