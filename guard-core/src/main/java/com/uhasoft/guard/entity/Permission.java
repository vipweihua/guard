package com.uhasoft.guard.entity;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class Permission {

  private String name;
  private String resource;
  private String type;
  private Limitation limitation;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Limitation getLimitation() {
    return limitation;
  }

  public void setLimitation(Limitation limitation) {
    this.limitation = limitation;
  }
}
