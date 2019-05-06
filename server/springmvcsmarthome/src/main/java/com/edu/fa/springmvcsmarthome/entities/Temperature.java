/*
 *  (C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date 8:35:51 AM Apr 7, 2019
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Temperature entity. Temperature Collection.
 *
 * @author linkx
 */
@Document(collection = "Temperature")
public class Temperature implements Serializable {
  /**
   * Serializable UID.
   */
  private static final long serialVersionUID = 1L;
  @Id
  private int temperatureId;
  @Field(value = "Temperature")
  private float temp;
  @Field(value = "TimeAdd")
  private Date timeAdd;

  public int getTemperatureId() {
    return temperatureId;
  }

  public void setTemperatureId(int temperatureId) {
    this.temperatureId = temperatureId;
  }

  public float getTemperature() {
    return temp;
  }

  public void setTemperature(float temp) {
    this.temp = temp;
  }

  public Date getTimeAdd() {
    return timeAdd;
  }

  public void setTimeAdd(Date timeAdd) {
    this.timeAdd = timeAdd;
  }

  public Temperature() {
    super();
  }

  /**
   * Create new Temperature object.
   *
   * @param temperatureId : ID.
   * @param temperature   : Temp.
   * @param timeAdd       : TimeAdd.
   */
  public Temperature(int temperatureId, float temp, Date timeAdd) {
    super();
    this.temperatureId = temperatureId;
    this.temp = temp;
    this.timeAdd = timeAdd;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Temperature [temperatureId=" + temperatureId + ", temp=" + temp
        + ", timeAdd=" + timeAdd + "]";
  }

}
