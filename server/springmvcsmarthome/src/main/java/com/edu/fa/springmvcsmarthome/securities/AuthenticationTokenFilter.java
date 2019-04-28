/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 9:27:41 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.securities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.edu.fa.springmvcsmarthome.entities.Role;
import com.edu.fa.springmvcsmarthome.entities.UserAccount;
import com.edu.fa.springmvcsmarthome.services.AuthenticationToken;
import com.edu.fa.springmvcsmarthome.services.UserAccountService;
import com.edu.fa.springmvcsmarthome.utils.AuthenticationTokenUtil;
import com.edu.fa.springmvcsmarthome.utils.Constants;

public class AuthenticationTokenFilter
    extends UsernamePasswordAuthenticationFilter {
  @Autowired
  private AuthenticationTokenUtil authenticationTokenUtil;
  @Autowired
  private UserAccountService userAccountService;
  @Autowired
  private AuthenticationToken authenticationToken;

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.security.web.authentication.
   * AbstractAuthenticationProcessingFilter#doFilter(javax.servlet.
   * ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
   */
  @Override
  public void doFilter(ServletRequest req, ServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    // TODO Auto-generated method stub
    HttpServletRequest httpServletRequest = (HttpServletRequest) req;
    String authToken = httpServletRequest.getHeader(Constants.TOKEN_HEADER);
    if (authenticationTokenUtil.isTokenExpired(authToken)) {
      String username = authenticationToken.getUsernameFromToken(authToken);
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
        chain.doFilter(httpServletRequest, res);
      } else {
        throw new UsernameNotFoundException(username);
      }
    }
  }

}
