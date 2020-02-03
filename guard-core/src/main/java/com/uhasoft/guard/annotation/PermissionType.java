package com.uhasoft.guard.annotation;

import java.lang.annotation.*;

/**
 * @author Weihua
 * @since 1.0.0
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface PermissionType {

  String value();
}
