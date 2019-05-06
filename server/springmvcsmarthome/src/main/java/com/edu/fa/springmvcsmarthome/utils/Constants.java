/*
 *  (C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date 9:06:17 AM Apr 6, 2019
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.utils;

import org.springframework.stereotype.Component;

@Component
public class Constants {
  public static final String LED_SEQ_KEY = "led";
  public static final String TEMP_SEQ_KEY = "temperature";
  public static final String HUMI_SEQ_KEY = "Humidity";
  public static final String RAIN_WATER_SENSOR = "RainWaterSensor";
  public static final String LIGHT_DEPENDENT_RESISTOR = "LightDependentResistor";
  public static final String AIR_CONDITIONER = "AirConditioner";
  public static final String USERNAME = "username";
  public static final String SECRET_KEY = "11111111111111111111111111111111";
  public static final long EXPIRE_TIME = 86400000;
  public static final String TOKEN_HEADER = "Authorization";
  public static final int YEAR = 2021;
  public static final int MONTH = 12;
  public static final int DAYOFMONTH = 31;
  public static final int HOUROFDAY = 23;
  public static final int MINUTE = 59;
  public static final int SECOND = 59;
  public static final String API_URI = "/api/**";

  private Constants() {
    super();
    // Auto-generated constructor stub
  }

}
