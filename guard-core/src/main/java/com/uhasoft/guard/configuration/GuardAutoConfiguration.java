package com.uhasoft.guard.configuration;

import com.uhasoft.guard.aop.ResourceAspect;
import com.uhasoft.guard.filter.AccessFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static com.uhasoft.guard.constant.GuardConstant.GUARD_STRICT_MODE;

/**
 * @author Weihua
 * @since 1.0.0
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class GuardAutoConfiguration {

  @Value("${" + GUARD_STRICT_MODE + ":false}")
  private boolean strictMode;

  @Bean
  public ResourceAspect resourceAspect(){
    return new ResourceAspect(strictMode);
  }

  @Bean
  public AccessFilter accessFilter(){
    return new AccessFilter();
  }
}
