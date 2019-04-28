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
  private float temperature;
  @Field(value = "TimeAdd")
  private Date timeAdd;

  public int getTemperatureId() {
    return temperatureId;
  }

  public void setTemperatureId(int temperatureId) {
    this.temperatureId = temperatureId;
  }

  public float getTemperature() {
    return temperature;
  }

  public void setTemperature(float temperature) {
    this.temperature = temperature;
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
   * TODO Create new Temperature object.
   *
   * @param temperatureId : ID.
   * @param temperature   : Temp.
   * @param timeAdd       : TimeAdd.
   */
  public Temperature(int temperatureId, float temperature, Date timeAdd) {
    super();
    this.temperatureId = temperatureId;
    this.temperature = temperature;
    this.timeAdd = timeAdd;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((timeAdd == null) ? 0 : timeAdd.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Temperature other = (Temperature) obj;
    if (timeAdd == null) {
      if (other.timeAdd != null) {
        return false;
      }
    } else if (!timeAdd.equals(other.timeAdd)) {
      return false;
    }
    return true;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Temperature [temperatureId=" + temperatureId + ", temperature=" + temperature
        + ", timeAdd=" + timeAdd + "]";
  }

}
