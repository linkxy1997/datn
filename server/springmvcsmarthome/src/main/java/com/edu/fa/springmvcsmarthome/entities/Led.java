
package com.edu.fa.springmvcsmarthome.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Led Entity.
 *
 * @author linkx
 *
 */
@Document(collection = "Led")
public class Led implements Serializable {

  /**
   * Serializable UID.
   *
   */
  private static final long serialVersionUID = 1L;
  @Transient
  public static final String SEQUENCE_NAME = "users_sequence";
  @Id
  private int ledId;
  @Field(value = "LedStt")
  private int ledStt;
  @Field(value = "TimeChange")
  private Date timeChange;

  public int getLedId() {
    return ledId;
  }

  public void setLedId(int ledId) {
    this.ledId = ledId;
  }

  public int getLedStt() {
    return ledStt;
  }

  public void setLedStt(int ledStt) {
    this.ledStt = ledStt;
  }

  public Date getTimeChange() {
    return timeChange;
  }

  public void setTimeChange(Date timeChange) {
    this.timeChange = timeChange;
  }

  /**
   * Create new Led.
   *
   * @param ledId      int
   * @param ledStt     byte
   * @param timeChange Date
   */
  public Led(int ledId, int ledStt, Date timeChange) {
    super();
    this.ledId = ledId;
    this.ledStt = ledStt;
    this.timeChange = timeChange;
  }

  public Led() {
    super();
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
    result = prime * result + ledId;
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
    Led other = (Led) obj;
    if (ledId != other.ledId) {
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
    return "Led [ledId=" + ledId + ", ledStt=" + ledStt + ", timeChange="
        + timeChange + "]";
  }
}
