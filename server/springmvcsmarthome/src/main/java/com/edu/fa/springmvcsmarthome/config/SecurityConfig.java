/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 27, 2019 8:36:05 AM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edu.fa.springmvcsmarthome.securities.ApiAuthenticationEntryPoint;
import com.edu.fa.springmvcsmarthome.securities.AuthenticationTokenFilter;
import com.edu.fa.springmvcsmarthome.securities.CustomAccessDeniedHandler;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Bean
  public AuthenticationTokenFilter authenticationTokenFilter()
      throws Exception {
    AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
    authenticationTokenFilter
        .setAuthenticationManager(this.authenticationManager());
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

  @Override
  protected AuthenticationManager authenticationManager() throws Exception {
    // TODO Auto-generated method stub
    return super.authenticationManager();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // TODO Auto-generated method stub
    http.csrf().ignoringAntMatchers("/api/**");
    http.authorizeRequests().antMatchers("/api/login**").permitAll();
    http.antMatcher("/api/**").httpBasic()
        .authenticationEntryPoint(this.apiAuthenticationEntryPoint()).and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests().antMatchers(HttpMethod.GET, "/api/hello")
        .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").and()
        .addFilterBefore(this.authenticationTokenFilter(),
            UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .accessDeniedHandler(this.customAccessDeniedHandler());
  }

}
