package com.uhasoft.guard.context;

import com.uhasoft.guard.entity.Limitation;
import com.uhasoft.guard.entity.User;

import java.util.List;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class UserContext {

  private User user;
  private String resource;
  private String rightType;

  public List<Limitation> getLimitation(){
    return user.getLimitation(rightType, resource);
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

  public String getRightType() {
    return rightType;
  }

  public void setRightType(String rightType) {
    this.rightType = rightType;
  }
}
