/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 27, 2019 8:36:05 AM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.edu.fa.springmvcsmarthome.securities.ApiAuthenticationEntryPoint;
import com.edu.fa.springmvcsmarthome.securities.AuthenticationTokenFilter;
import com.edu.fa.springmvcsmarthome.securities.CustomAccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private UserDetailsService userDetailsService;

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
    // TODO Auto-generated method stub
    return super.authenticationManager();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(encoder());
    return authProvider;
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // TODO Auto-generated method stub
    http.csrf().ignoringAntMatchers("/api/**").disable();

    http.authorizeRequests().antMatchers("/login**").permitAll();

    http.antMatcher("/api/**").httpBasic()
        .authenticationEntryPoint(apiAuthenticationEntryPoint()).and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests().antMatchers(HttpMethod.GET, "/api/**")
        .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.DELETE, "/api/**")
        .access("hasRole('ROLE_ADMIN')").and()
        .addFilterBefore(authenticationTokenFilter(),
            UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
  }

}
