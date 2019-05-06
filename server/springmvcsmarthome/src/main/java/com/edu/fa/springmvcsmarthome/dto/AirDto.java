/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 3, 2019 3:51:31 PM
 * @version 1.0
 */
/**
 * 
 */
package com.edu.fa.springmvcsmarthome.dto;

import java.io.Serializable;

/**
 * @author linkx
 *
 */
public class AirDto implements Serializable {

  private static final long serialVersionUID = 152441275526035566L;
  private int id;
  private int airStt;

  public int getId() {
    return this.id;
  }

  public void setAirId(int id) {
    this.id = id;
  }

  public int getAirStt() {
    return airStt;
  }

  public void setAirStt(int airStt) {
    this.airStt = airStt;
  }

  public AirDto(int id, int airStt) {
    super();
    this.id = id;
    this.airStt = airStt;
  }

  public AirDto() {
    super();
    // Auto-generated constructor stub
  }

}
