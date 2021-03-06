/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 27, 2019 8:36:05 AM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.edu.fa.springmvcsmarthome.securities.ApiAuthenticationEntryPoint;
import com.edu.fa.springmvcsmarthome.securities.AuthenticationTokenFilter;
import com.edu.fa.springmvcsmarthome.securities.CustomAccessDeniedHandler;
import com.edu.fa.springmvcsmarthome.utils.Constants;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public AuthenticationTokenFilter authenticationTokenFilter()
      throws Exception {
    AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
    authenticationTokenFilter.setAuthenticationManager(authenticationManager());
    return authenticationTokenFilter;
  }

  @Bean
  public ApiAuthenticationEntryPoint apiAuthenticationEntryPoint() {
    return new ApiAuthenticationEntryPoint();
  }

  @Bean
  public CustomAccessDeniedHandler customAccessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  @Bean
  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    // Auto-generated method stub
    return super.authenticationManager();
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Auto-generated method stub
    http.csrf().ignoringAntMatchers(Constants.API_URI).disable();
    http.authorizeRequests().antMatchers("/api/login**").permitAll();
    http.antMatcher(Constants.API_URI).httpBasic()
        .authenticationEntryPoint(apiAuthenticationEntryPoint()).and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests().antMatchers(HttpMethod.GET, Constants.API_URI)
        .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        .antMatchers(HttpMethod.POST, Constants.API_URI)
        .access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.DELETE, Constants.API_URI)
        .access("hasRole('ROLE_ADMIN')").and()
        .addFilterBefore(authenticationTokenFilter(),
            UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
  }

}
