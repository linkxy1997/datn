/*
 *	(C) Copyright 2019 STS. All Rights Reserved
 *
 * @author linkx
 * @date Apr 26, 2019 8:43:43 PM
 * @version 1.0
 */
/**
 *
 */

package com.edu.fa.springmvcsmarthome.entities;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author linkx
 *
 */

@Document(collection = "UserAccount")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = { "roles", "authorities" })
public class UserAccount implements Serializable {

  private static final long serialVersionUID = 1614543032632325093L;
  @Id
  private int id;
  @Field(value = "Username")
  private String username;
  @Field(value = "Password")
  private String password;
  @DBRef(lazy = true)
  @Field(value = "Role")
  private List<Role> roles;

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "UserAccount [id=" + id + ", username=" + username + ", password="
        + password + ", roles=" + roles + "]";
  }

  public UserAccount() {
    super();
  }

  public UserAccount(int id, String username, String password,
      List<Role> roles) {
    super();
    this.id = id;
    this.username = username;
    this.password = password;
    this.roles = roles;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}
