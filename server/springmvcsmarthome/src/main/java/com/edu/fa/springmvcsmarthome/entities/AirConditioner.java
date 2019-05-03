/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 25, 2019 4:05:06 PM
 * @version 1.0
 */
/**
 *
 */

package com.edu.fa.springmvcsmarthome.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author linkx
 *
 */
@Document(collection = "AirConditioner")
public class AirConditioner implements Serializable {

  private static final long serialVersionUID = 4794101587625840803L;
  @Id
  private int id;
  @Field(value = "AirConditionerStt")
  private int airStt;
  @Field(value = "TimeChange")
  private Date timeChange;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAirStt() {
    return airStt;
  }

  public void setAirStt(int airStt) {
    this.airStt = airStt;
  }

  public Date getTimeChange() {
    return timeChange;
  }

  public void setTimeChange(Date timeChange) {
    this.timeChange = timeChange;
  }

  public AirConditioner(int id, int airStt, Date timeChange) {
    super();
    this.id = id;
    this.airStt = airStt;
    this.timeChange = timeChange;
  }

  public AirConditioner() {
    super();
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "AirConditioner [id=" + id + ", airStt=" + airStt + ", timeChange="
        + timeChange + "]";
  }

}
