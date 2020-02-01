package com.uhasoft.guard.ds.json.entity;

import java.util.List;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class RolePermission {

  private String name;
  private List<String> permissions;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }
}
