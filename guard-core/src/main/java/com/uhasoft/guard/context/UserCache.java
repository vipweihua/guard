package com.uhasoft.guard.context;

import com.uhasoft.guard.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class UserCache {

  private static final Map<String, User> CACHE = new HashMap<>();

  public static void addUser(String name, User user){
    CACHE.put(name, user);
  }

  public static User getUser(String name){
    return CACHE.get(name);
  }
}
