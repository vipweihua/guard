package com.uhasoft.guard.demo.controller;

import com.uhasoft.guard.annotation.Resource;
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
@Resource("order")
public class Controller {

//  @Retrieve
  @GetMapping("order")
  public String getOrderLimitation(){
    return UserThreadLocal.getLimitation().toString();
  }

  @Resource("insurance")
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
