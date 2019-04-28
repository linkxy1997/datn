/*
 *  (C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date 8:47:26 AM Apr 7, 2019
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Humidity entity. Humidity Collection
 *
 * @author linkx
 */
@Document(collection = "Humidity")
public class Humidity implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  private int humidityId;
  @Field(value = "Humidity")
  private float humidity;
  @Field(value = "TimeAdd")
  private Date timeAdd;

  public int getHumidityId() {
    return humidityId;
  }

  public void setHumidityId(int humidityId) {
    this.humidityId = humidityId;
  }

  public float getHumidity() {
    return humidity;
  }

  public void setHumidity(float humidity) {
    this.humidity = humidity;
  }

  public Date getTimeAdd() {
    return timeAdd;
  }

  public void setTimeAdd(Date timeAdd) {
    this.timeAdd = timeAdd;
  }

  public Humidity() {
    super();
  }

  /**
   * TODO Create new Humidity object.
   *
   * @param humidityId : Id.
   * @param humidity   : Humidity.
   * @param timeAdd    : timeAdd.
   */
  public Humidity(int humidityId, float humidity, Date timeAdd) {
    super();
    this.humidityId = humidityId;
    this.humidity = humidity;
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
    result = prime * result + humidityId;
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
    Humidity other = (Humidity) obj;
    if (humidityId != other.humidityId) {
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
    return "Humidity [humidityId=" + humidityId + ", humidity=" + humidity + ", timeAdd=" + timeAdd
        + "]";
  }
}
