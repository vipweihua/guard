package com.uhasoft.guard.entity;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class Limitation {

  private String left;
  private String operator;
  private String right;

  public String getLeft() {
    return left;
  }

  public void setLeft(String left) {
    this.left = left;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getRight() {
    return right;
  }

  public void setRight(String right) {
    this.right = right;
  }

  public String toString(){
    return left + " " + operator + " " + right;
  }
}
