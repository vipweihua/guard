package com.uhasoft.guard.demo.mybatis.mapper;

import com.github.pagehelper.Page;
import com.uhasoft.guard.demo.mybatis.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Weihua
 * @since 1.0.0
 */
@Mapper
public interface OrderMapper {

  @Select("select * from t_order o where o.id = #{id}")
  Order findById(@Param("id") String id);

  @Select("select * from t_order")
  Page<Order> findAll();

  @Insert("insert into t_order(id, createdBy, createdAt, lastModifiedBy, lastModifiedAt, version, deleted, area) " +
      "values(#{o.id}, #{o.createdBy}, #{o.createdAt}, #{o.lastModifiedBy}, #{o.lastModifiedAt}, #{o.version}, #{o.deleted}, #{o.area})")
  int insert(@Param("o") Order order);
}
