package com.uhasoft.guard.demo.mybatis;

import com.github.pagehelper.PageHelper;
import com.uhasoft.guard.demo.mybatis.entity.Order;
import com.uhasoft.guard.demo.mybatis.mapper.OrderMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Weihua
 * @since 1.0.0
 */
@SpringBootApplication
@RestController
@RequestMapping("order")
public class MybatisApplication {

  private OrderMapper orderMapper;

  public MybatisApplication(OrderMapper orderMapper){
    this.orderMapper = orderMapper;
  }

  public static void main(String[] args) {
    SpringApplication.run(MybatisApplication.class, args);
  }

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
    return orderMapper.findAll();
  }
}
