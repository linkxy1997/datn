/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 22, 2019 9:24:34 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "RainWaterSensor")
public class RainWaterSensor implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 7287891982455489925L;
  @Id
  private int rainId;
  @Field(value = "RainStatus")
  private int raintStatus;
  @Field(value = "TimeChange")
  private Date timeChange;

  public int getRainId() {
    return rainId;
  }

  public void setRainId(int rainId) {
    this.rainId = rainId;
  }

  public int getRaintStatus() {
    return raintStatus;
  }

  public void setRaintStatus(int raintStatus) {
    this.raintStatus = raintStatus;
  }

  public Date getTimeChange() {
    return timeChange;
  }

  public void setTimeChange(Date timeChange) {
    this.timeChange = timeChange;
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
    result = prime * result + rainId;
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
    RainWaterSensor other = (RainWaterSensor) obj;
    if (rainId != other.rainId) {
      return false;
    }
    return true;
  }

  public RainWaterSensor(int rainId, int raintStatus, Date timeChange) {
    super();
    this.rainId = rainId;
    this.raintStatus = raintStatus;
    this.timeChange = timeChange;
  }

  public RainWaterSensor() {
    super();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "RainWaterSensor [rainId=" + rainId + ", raintStatus=" + raintStatus + ", timeChange="
        + timeChange
        + "]";
  }

}
