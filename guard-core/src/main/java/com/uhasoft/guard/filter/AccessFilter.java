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
//    List<Right> rights = new ArrayList<>();
//    Right right = new Right();
//    right.setLimitation("area = 'shanghai'");
//    right.setResource("order");
//    right.setType("right");
//    rights.add(right);
//
//    Right another = new Right();
//    another.setLimitation("area = 'hangzhou'");
//    another.setResource("insurance");
//    another.setType("retrieve");
//    rights.add(another);
//
//    UserContext.setRights(rights);
    filterChain.doFilter(request, response);
  }
}
