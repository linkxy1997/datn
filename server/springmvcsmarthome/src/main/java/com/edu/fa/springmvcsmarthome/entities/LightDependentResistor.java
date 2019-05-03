/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 22, 2019 9:29:43 PM
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
@Document(collection = "LightDependentResistor")
public class LightDependentResistor implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 9106095850389642728L;
  @Id
  private int id;
  @Field(value = "LightDependent")
  private int lightDependent;
  @Field(value = "TimeChange")
  private Date timeChange;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getLightDependent() {
    return lightDependent;
  }

  public void setLightDependent(int lightDependent) {
    this.lightDependent = lightDependent;
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
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "LightDependentResistor [id=" + id + ", lightDependent="
        + lightDependent + ", timeChange=" + timeChange + "]";
  }

  public LightDependentResistor(int id, int lightDependent, Date timeChange) {
    super();
    this.id = id;
    this.lightDependent = lightDependent;
    this.timeChange = timeChange;
  }

  public LightDependentResistor() {
    super();
  }

}
