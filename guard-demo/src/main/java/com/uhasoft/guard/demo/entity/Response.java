package com.uhasoft.guard.demo.entity;

/**
 * @author Weihua
 * @since 1.0.0
 */
public class Response<T> {

  private int status;
  private String message;
  private T data;

  public Response(int status, String message, T data){
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public static <T> Response<T> success(T data){
    return new Response<>(200, null, data);
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
