
package com.edu.fa.springmvcsmarthome.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Logging all method.
 *
 * @author linkx
 *
 */
@Aspect
@Configuration
public class LoggingAspect {
  private static final Logger LOGGER = LoggerFactory
      .getLogger(LoggingAspect.class);

  /**
   * This function TODO logging BeforeGetCurrentLedStt method.
   *
   * @param joinPoint is BeforeGetCurrentLedStt.
   */
  @Before("execution(* com.edu.fa.springmvcsmarthome.services.impl.LedServiceImpl."
      + "getCurrentLedStt(..))")
  public void logBeforeGetCurrentLedStt(JoinPoint joinPoint) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("****LoggingAspect.logGetCurrentLedStt() : "
          + joinPoint.toLongString());
    }
  }

  /**
   * This function TODO logging BeforeSaveLed method.
   *
   * @param joinPoint jointPoint is BeforeSaveLed.
   */
  @Before("execution(* com.edu.fa.springmvcsmarthome.services.impl.LedServiceImpl.save(..))")
  public void logBeforeSaveLed(JoinPoint joinPoint) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("****LoggingAspect.logBeforeSaveLed() : "
          + joinPoint.getSignature().getName());
    }
  }
}
