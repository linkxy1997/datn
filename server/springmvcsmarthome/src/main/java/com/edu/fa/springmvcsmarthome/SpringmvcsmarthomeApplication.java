package com.edu.fa.springmvcsmarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringmvcsmarthomeApplication
    extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(SpringmvcsmarthomeApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(
      SpringApplicationBuilder builder) {
    // TODO Auto-generated method stub
    return builder.sources(SpringmvcsmarthomeApplication.class);
  }

}
