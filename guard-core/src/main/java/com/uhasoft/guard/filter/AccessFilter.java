package com.uhasoft.guard.filter;

import com.uhasoft.guard.context.UserCache;
import com.uhasoft.guard.context.UserThreadLocal;
import com.uhasoft.guard.entity.User;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class AccessFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String userName = request.getHeader("user");
    User user = UserCache.getUser(userName);
    //TODO get user from cache
    UserThreadLocal.setCurrentUser(user);
//    List<Permission> permissions = new ArrayList<>();
//    Permission permission = new Permission();
//    permission.setLimitation("area = 'shanghai'");
//    permission.setResource("order");
//    permission.setType("retrieve");
//    permissions.add(permission);
//
//    Permission another = new Permission();
//    another.setLimitation("area = 'hangzhou'");
//    another.setResource("insurance");
//    another.setType("retrieve");
//    permissions.add(another);
//
//    UserContext.setPermissions(permissions);
    filterChain.doFilter(request, response);
  }
}
