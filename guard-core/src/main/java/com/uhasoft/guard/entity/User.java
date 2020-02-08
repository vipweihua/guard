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

  public Set<Permission> getPermissions() {
    Set<List<Permission>> permissions = roles.stream().map(Role::getPermissions).collect(Collectors.toSet());
    Set<Permission> allButNonDuplicated = new HashSet<>();
    permissions.forEach(allButNonDuplicated::addAll);
    return allButNonDuplicated;
  }

  public List<Limitation> getLimitation(String type, String resource) {
    return getPermissions().stream()
        .filter(p -> p.getType().equalsIgnoreCase(type) && p.getResource().equalsIgnoreCase(resource))
        .map(Permission::getLimitation)
        .collect(Collectors.toList());
  }

}
