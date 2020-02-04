package com.uhasoft.guard.demo.mybatis.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author Weihua
 * @since 1.0.0
 */
@Data
@ToString
public class Order {

  private String id;
  private String createdBy;
  private Date createdAt;
  private String lastModifiedBy;
  private Date lastModifiedAt;
  private int version;
  private boolean deleted;

  private String area;

}
