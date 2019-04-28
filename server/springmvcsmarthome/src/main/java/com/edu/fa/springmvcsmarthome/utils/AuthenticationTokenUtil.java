/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 9:08:49 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.utils;

import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Component
public class AuthenticationTokenUtil {
  public static final Logger LOGGER = LoggerFactory
      .getLogger(AuthenticationTokenUtil.class);

  public JWTClaimsSet getClaimsFromToken(String token) {
    JWTClaimsSet claims = null;
    try {
      SignedJWT signedJWT = SignedJWT.parse(token);
      JWSVerifier verifier = new MACVerifier(generateShareSecret());
      if (signedJWT.verify(verifier)) {
        claims = signedJWT.getJWTClaimsSet();
      }
    } catch (ParseException | JOSEException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return claims;
  }

  public Date generateExpirationDate() {
    return new Date(System.currentTimeMillis() + Constants.EXPIRE_TIME);
  }

  public Date getExpirationDateFromToken(String token) {
    Date expiration = null;
    JWTClaimsSet claims = getClaimsFromToken(token);
    expiration = claims.getExpirationTime();
    return expiration;
  }

  public byte[] generateShareSecret() {
    // Generate 256-bit (32-byte) shared secret
    byte[] sharedSecret = new byte[32];
    sharedSecret = Constants.SECRET_KEY.getBytes();
    return sharedSecret;
  }

  public boolean isTokenExpired(String token) {
    Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }
}
