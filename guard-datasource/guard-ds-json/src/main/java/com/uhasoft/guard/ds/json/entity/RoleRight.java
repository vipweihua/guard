package com.uhasoft.guard.ds.json.entity;

import java.util.List;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class RoleRight {

  private String name;
  private List<String> rights;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getRights() {
    return rights;
  }

  public void setRights(List<String> rights) {
    this.rights = rights;
  }
}
