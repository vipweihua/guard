package com.uhasoft.guard.demo.controller;

import com.uhasoft.guard.annotation.PermissionType;
import com.uhasoft.guard.annotation.GuardResource;
import com.uhasoft.guard.annotation.Retrieve;
import com.uhasoft.guard.context.UserThreadLocal;
import com.uhasoft.guard.demo.entity.Order;
import com.uhasoft.guard.demo.entity.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Weihua
 * @since 1.0.0
 */
@GuardResource("order")
@RequestMapping("order")
@RestController
public class OrderController extends BaseController<Order> {

  @GetMapping("id/{id}")
  public Response<List<String>> findById(@PathVariable String id){
    return Response.success(UserThreadLocal.getLimitation());
  }

  @Retrieve
  @PostMapping("search")
  public Response<List<String>> search(@RequestBody Map<String, String> searchBean){
    return Response.success(UserThreadLocal.getLimitation());
  }

  /**
   * 自定义PermissionType
   * @param id 订单ID
   * @return 订单
   */
  @PermissionType(type = "deliver")
  @PutMapping("id/{id}")
  public Response<List<String>> deliver(@PathVariable String id){
    return Response.success(UserThreadLocal.getLimitation());
  }
}
