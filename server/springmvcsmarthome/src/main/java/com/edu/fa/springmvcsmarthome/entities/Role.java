/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 8:50:18 PM
 * @version 1.0
 */

package com.edu.fa.springmvcsmarthome.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Role")
public class Role implements Serializable {

  private static final long serialVersionUID = 1018795899856254996L;
  @Id
  private int id;
  @Field(value = "Role")
  private String roleName;

  public Role() {
    super();
  }

  public Role(int id, String roleName) {
    super();
    this.id = id;
    this.roleName = roleName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Role [id=" + id + ", roleName=" + roleName + "]";
  }
}
