package com.uhasoft.guard.entity;

import java.util.List;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class Role {

  private String name;
  private List<Permission> permissions;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<Permission> permissions) {
    this.permissions = permissions;
  }
}
