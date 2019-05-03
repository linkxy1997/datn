
package com.edu.fa.springmvcsmarthome.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * @author linkx
 *
 */
@Document(collection = "Sequence")
public class Sequence implements Serializable {
  /** Serializable UID. */
  private static final long serialVersionUID = 1L;

  @Id
  private String id;

  private int seq;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getSeq() {
    return seq;
  }

  public void setSeq(int seq) {
    this.seq = seq;
  }

  public Sequence(String id, int seq) {
    super();
    this.id = id;
    this.seq = seq;
  }

  public Sequence() {
    super();
    // Auto-generated constructor stub
  }

}
