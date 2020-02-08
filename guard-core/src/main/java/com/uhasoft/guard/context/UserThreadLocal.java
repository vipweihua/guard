package com.uhasoft.guard.context;

import com.uhasoft.guard.entity.Limitation;
import com.uhasoft.guard.entity.User;

import java.util.List;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class UserThreadLocal {

  private static final ThreadLocal<UserContext> currentUser = ThreadLocal.withInitial(UserContext::new);

  public static void setCurrentUser(User user){
    currentUser.get().setUser(user);
  }

  public static void setResource(String resource){
    currentUser.get().setResource(resource);
  }

  public static void setPermissionType(String type){
    currentUser.get().setPermissionType(type);
  }

  public static List<Limitation> getLimitation(){
    return currentUser.get().getLimitation();
  }
}
