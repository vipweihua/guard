package com.uhasoft.guard.context;

import com.uhasoft.guard.entity.User;

import java.util.List;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class UserContext {

  private User user;
  private String resource;
  private String permissionType;

  public List<String> getLimitation(){
    return user.getLimitation(permissionType, resource);
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getPermissionType() {
    return permissionType;
  }

  public void setPermissionType(String permissionType) {
    this.permissionType = permissionType;
  }
}
