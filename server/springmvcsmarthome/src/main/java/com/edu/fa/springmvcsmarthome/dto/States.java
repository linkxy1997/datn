/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 22, 2019 9:35:34 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.dto;

import org.springframework.stereotype.Component;

@Component
public class States {
  private int ledStt;
  private float humidity;
  private float temperature;
  private int lightDependent;
  private int raintStatus;
  private int airStt;

  public int getAirStt() {
    return airStt;
  }

  public void setAirStt(int airStt) {
    this.airStt = airStt;
  }

  public int getLedStt() {
    return ledStt;
  }

  public void setLedStt(int ledStt) {
    this.ledStt = ledStt;
  }

  public float getHumidity() {
    return humidity;
  }

  public void setHumidity(float humidity) {
    this.humidity = humidity;
  }

  public float getTemperature() {
    return temperature;
  }

  public void setTemperature(float temperature) {
    this.temperature = temperature;
  }

  public int getLightDependent() {
    return lightDependent;
  }

  public void setLightDependent(int lightDependent) {
    this.lightDependent = lightDependent;
  }

  public int getRaintStatus() {
    return raintStatus;
  }

  public void setRaintStatus(int raintStatus) {
    this.raintStatus = raintStatus;
  }

  public States(int ledStt, float humidity, float temperature,
      int lightDependent, int raintStatus, int airStt) {
    super();
    this.ledStt = ledStt;
    this.humidity = humidity;
    this.temperature = temperature;
    this.lightDependent = lightDependent;
    this.raintStatus = raintStatus;
    this.airStt = airStt;
  }

  public States() {
    super();
  }

}
