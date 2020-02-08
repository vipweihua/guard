package com.uhasoft.guard.demo.mybatis.controller;

import com.github.pagehelper.PageHelper;
import com.uhasoft.guard.annotation.GuardResource;
import com.uhasoft.guard.annotation.RightType;
import com.uhasoft.guard.annotation.Retrieve;
import com.uhasoft.guard.context.UserThreadLocal;
import com.uhasoft.guard.demo.mybatis.entity.Order;
import com.uhasoft.guard.demo.mybatis.entity.Response;
import com.uhasoft.guard.demo.mybatis.mapper.OrderMapper;
import com.uhasoft.guard.entity.Limitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

  @Autowired
  private OrderMapper orderMapper;

  @GetMapping
  public List<Order> findAll(@RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "10") int pageSize){

    //第一次运行时，请打开如下注释添加些测试数据，之后可以继续注释掉
//    for(int i = 0; i < 20; i++){
//      Order order = new Order();
//      order.setArea(i%2==0?"Shanghai":"hangzhou");
//      order.setId("ID" + i);
//      order.setCreatedAt(new Date());
//      order.setCreatedBy("admin");
//      order.setLastModifiedAt(new Date());
//      order.setLastModifiedBy("admin");
//      order.setVersion(0);
//      order.setDeleted(false);
//      orderMapper.insert(order);
//    }
    PageHelper.startPage(pageNum, pageSize);
    return orderMapper.findAll(pageNum, pageSize);
  }
  @GetMapping("id/{id}")
  public Response<List<Limitation>> findById(@PathVariable String id){
    return Response.success(UserThreadLocal.getLimitation());
  }

  @Retrieve
  @PostMapping("search")
  public Response<List<Limitation>> search(@RequestBody Map<String, String> searchBean){
    return Response.success(UserThreadLocal.getLimitation());
  }

  /**
   * 自定义RightType
   * @param id 订单ID
   * @return 订单
   */
  @RightType("deliver")
  @PutMapping("id/{id}")
  public Response<List<Limitation>> deliver(@PathVariable String id){
    return Response.success(UserThreadLocal.getLimitation());
  }
}
