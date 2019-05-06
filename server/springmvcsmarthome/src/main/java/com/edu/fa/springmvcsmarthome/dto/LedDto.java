/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date May 3, 2019 4:02:06 PM
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
public class LedDto implements Serializable {
  private static final long serialVersionUID = 4739505014555626371L;
  private int ledId;
  private int ledStt;

  public int getId() {
    return this.ledId;
  }

  public void setId(int ledId) {
    this.ledId = ledId;
  }

  public int getStt() {
    return this.ledStt;
  }

  public void setStt(int ledStt) {
    this.ledStt = ledStt;
  }
}
