/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 9:27:41 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.securities;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.edu.fa.springmvcsmarthome.entities.Role;
import com.edu.fa.springmvcsmarthome.entities.UserAccount;
import com.edu.fa.springmvcsmarthome.services.AuthenticationTokenService;
import com.edu.fa.springmvcsmarthome.services.UserAccountService;
import com.edu.fa.springmvcsmarthome.utils.Constants;

public class AuthenticationTokenFilter
    extends UsernamePasswordAuthenticationFilter {

  @Autowired
  private UserAccountService userAccountService;
  @Autowired
  private AuthenticationTokenService authenticationTokenService;
  private static final Logger LOGGER = LoggerFactory
      .getLogger(AuthenticationTokenFilter.class);

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.security.web.authentication.
   * AbstractAuthenticationProcessingFilter#doFilter(javax.servlet.
   * ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    // Auto-generated method stub
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String authToken = httpServletRequest.getHeader(Constants.TOKEN_HEADER);
    String username = null;
    try {
      if (authenticationTokenService.validateTokenLogin(authToken)) {
        username = authenticationTokenService.getUsernameFromToken(authToken);
      }
    } catch (ParseException | NullPointerException e) {
      // Auto-generated catch block
      if (LOGGER.isErrorEnabled()) {
        LOGGER.error(e.getMessage(), e);
      }
    }
    Optional<UserAccount> optional = userAccountService
        .findByUsername(username);
    if (optional.isPresent()) {
      UserAccount userAccount = optional.get();
      boolean enabled = true;
      boolean accountNonExpired = true;
      boolean credentialsNonExpired = true;
      boolean accountNonLocked = true;
      List<GrantedAuthority> authorities = new ArrayList<>();
      for (Role role : userAccount.getRoles()) {
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
      }
      UserDetails userDetails = new User(username, userAccount.getPassword(),
          enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
          authorities);
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities());
      authentication
          .setDetails(new WebAuthenticationDetails(httpServletRequest));
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    chain.doFilter(request, response);
  }
}
