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
public class Automation implements Serializable {

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

  public void setAutomationState(int autoState) {
    this.automationState = autoState;
  }

  public Automation(int id, int autoState) {
    super();
    this.id = id;
    this.automationState = autoState;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "AutomationState [id=" + id + ", automationState=" + automationState
        + "]";
  }

  public Automation() {
    super();
  }

}
