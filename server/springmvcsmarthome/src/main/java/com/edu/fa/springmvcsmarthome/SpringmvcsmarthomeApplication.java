package com.edu.fa.springmvcsmarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 
 * @author linkx
 *
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringmvcsmarthomeApplication
    extends SpringBootServletInitializer {
  /**
   * entrypoint of system.
   * 
   * @param args String arrays.
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringmvcsmarthomeApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(
      final SpringApplicationBuilder builder) {
    // Auto-generated method stub
    return builder.sources(SpringmvcsmarthomeApplication.class);
  }

}
