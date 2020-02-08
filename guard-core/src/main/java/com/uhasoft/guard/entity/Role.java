package com.uhasoft.guard.entity;

import java.util.List;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class Role {

  private String name;
  private List<Right> rights;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Right> getRights() {
    return rights;
  }

  public void setRights(List<Right> rights) {
    this.rights = rights;
  }
}
