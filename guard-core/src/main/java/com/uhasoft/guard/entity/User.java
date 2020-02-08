package com.uhasoft.guard.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class User {

  private String name;
  private List<Role> roles;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public Set<Right> getRights() {
    Set<List<Right>> rights = roles.stream().map(Role::getRights).collect(Collectors.toSet());
    Set<Right> allButNonDuplicated = new HashSet<>();
    rights.forEach(allButNonDuplicated::addAll);
    return allButNonDuplicated;
  }

  public List<Limitation> getLimitation(String type, String resource) {
    return getRights().stream()
        .filter(p -> p.getType().equalsIgnoreCase(type) && p.getResource().equalsIgnoreCase(resource))
        .map(Right::getLimitation)
        .collect(Collectors.toList());
  }

}
