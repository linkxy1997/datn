/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 30, 2019 12:27:09 PM
 * @version 1.0
 */
package com.edu.fa.springmvcsmarthome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class WebConfig implements WebMvcConfigurer {
  @Bean
  // Only used when running in embedded servlet
  public DispatcherServlet dispatcherServlet() {
    return new DispatcherServlet();
  }

  @Override
  public void configureDefaultServletHandling(
      DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // Auto-generated method stub
    registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST",
        "DELETE", "PATCH");
  }

}
