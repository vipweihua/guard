package com.uhasoft.guard.demo.json.controller;

import com.uhasoft.guard.annotation.GuardResource;
import com.uhasoft.guard.context.UserThreadLocal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Weihua
 * @since 1.0.0
 */
@RestController
@RequestMapping
@GuardResource("order")
public class Controller {

//  @Retrieve
  @GetMapping("order")
  public String getOrderLimitation(){
    return UserThreadLocal.getLimitation().toString();
  }

  @GuardResource("insurance")
//  @Create
  @GetMapping("insurance")
  public String getInsuranceLimitation(){
    return UserThreadLocal.getLimitation().toString();
  }

//  @Create
  @PostMapping("order")
  public String createOrder(){
    return UserThreadLocal.getLimitation().toString();
  }
}
