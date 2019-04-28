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

import com.edu.fa.springmvcsmarthome.services.AuthenticationToken;
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
public class AuthenticationTokenImpl implements AuthenticationToken {
  @Autowired
  private AuthenticationTokenUtil authenticationTokenUtil;
  private static final Logger LOGGER = LoggerFactory
      .getLogger(AuthenticationTokenImpl.class);

  @Override
  public String generateTokenLogin(String username) {
    // TODO Auto-generated method stub
    String token = null;
    try {
      // TODO Create HMAC signer
      JWSSigner signer = new MACSigner(
          authenticationTokenUtil.generateShareSecret());
      JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
      builder.expirationTime(authenticationTokenUtil.generateExpirationDate());
      JWTClaimsSet claimsSet = builder.build();
      JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
      SignedJWT signedJWT = new SignedJWT(jwsHeader, claimsSet);
      // TODO Apply the HMAC protection
      signedJWT.sign(signer);
      // TODO Serialize to compact form, produces something like
      // eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
      token = signedJWT.serialize();
    } catch (JOSEException e) {
      // TODO Auto-generated catch block
      LOGGER.error(e.getMessage(), e);
    }
    return token;
  }

  @Override
  public boolean validateTokenLogin(String token) {
    // TODO Auto-generated method stub
    if (token == null || token.trim().length() == 0) {
      return false;
    }
    String username = getUsernameFromToken(token);
    if (username == null || username.isEmpty()) {
      return false;
    }
    if (authenticationTokenUtil.isTokenExpired(token)) {
      return false;
    }
    return true;
  }

  @Override
  public String getUsernameFromToken(String token) {
    // TODO Auto-generated method stub
    String username = null;

    JWTClaimsSet claims = authenticationTokenUtil.getClaimsFromToken(token);
    try {
      username = claims.getStringClaim(Constants.USERNAME);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      LOGGER.error(e.getMessage(), e);
    }

    return username;
  }

}
