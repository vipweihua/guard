package com.uhasoft.guard.ds.json.entity;

import java.util.List;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class UserRole {

  private String name;
  private List<String> roles;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }
}
