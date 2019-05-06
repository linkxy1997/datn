/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 3, 2019 4:05:12 PM
 * @version 1.0
 */
/**
 * 
 */
package com.edu.fa.springmvcsmarthome.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author linkx
 *
 */
public class TemperatureDto implements Serializable {
  private static final long serialVersionUID = -2347006241994106183L;
  private int temperatureId;
  private float temp;
  private Date timeAdd;

  public int getTemperatureId() {
    return temperatureId;
  }

  public void setTemperatureId(int temperatureId) {
    this.temperatureId = temperatureId;
  }

  public float getTemp() {
    return temp;
  }

  public void setTemp(float temp) {
    this.temp = temp;
  }

  public Date getTimeAdd() {
    return timeAdd;
  }

  public void setTimeAdd(Date timeAdd) {
    this.timeAdd = timeAdd;
  }

}
