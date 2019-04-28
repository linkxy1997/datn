/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 28, 2019 3:35:59 PM
 * @version 1.0
 */
/**
 * 
 */
package com.edu.fa.springmvcsmarthome.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author linkx
 *
 */
@Document(collection = "AutomationState")
public class AutomationState implements Serializable {

  private static final long serialVersionUID = -4853986089801157374L;
  @Id
  private int id;
  @Field(name = "AutomationState")
  private int automationState;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAutomationState() {
    return automationState;
  }

  public void setAutomationState(int automationState) {
    this.automationState = automationState;
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
    result = prime * result + id;
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AutomationState other = (AutomationState) obj;
    if (id != other.id)
      return false;
    return true;
  }

  public AutomationState(int id, int automationState) {
    super();
    this.id = id;
    this.automationState = automationState;
  }

  @Override
  public String toString() {
    return "AutomationState [id=" + id + ", automationState=" + automationState
        + "]";
  }

  public AutomationState() {
    super();
  }

}
