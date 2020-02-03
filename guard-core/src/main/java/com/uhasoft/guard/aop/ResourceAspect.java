package com.uhasoft.guard.aop;

import com.uhasoft.guard.annotation.Create;
import com.uhasoft.guard.annotation.Delete;
import com.uhasoft.guard.annotation.GuardResource;
import com.uhasoft.guard.annotation.PermissionType;
import com.uhasoft.guard.annotation.Retrieve;
import com.uhasoft.guard.annotation.Update;
import com.uhasoft.guard.constant.GuardConstant;
import com.uhasoft.guard.context.UserThreadLocal;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Weihua
 * @since 1.0.0
 */
@Aspect
public class ResourceAspect {

  private static final Map<Class<? extends Annotation>, String> PERMISSION_TYPE_MAP = new HashMap<>();
  private static final List<Class<? extends Annotation>> CRUD_ANNOTATION = new ArrayList<>();
  private static final List<Class<? extends Annotation>> STRICT_ANNOTATION = new ArrayList<>();

  static {
    PERMISSION_TYPE_MAP.put(Create.class, GuardConstant.PERMISSION_CREATE);
    PERMISSION_TYPE_MAP.put(Retrieve.class, GuardConstant.PERMISSION_RETRIEVE);
    PERMISSION_TYPE_MAP.put(Update.class, GuardConstant.PERMISSION_UPDATE);
    PERMISSION_TYPE_MAP.put(Delete.class, GuardConstant.PERMISSION_DELETE);
    PERMISSION_TYPE_MAP.put(PostMapping.class, GuardConstant.PERMISSION_CREATE);
    PERMISSION_TYPE_MAP.put(GetMapping.class, GuardConstant.PERMISSION_RETRIEVE);
    PERMISSION_TYPE_MAP.put(PutMapping.class, GuardConstant.PERMISSION_UPDATE);
    PERMISSION_TYPE_MAP.put(DeleteMapping.class, GuardConstant.PERMISSION_DELETE);

    CRUD_ANNOTATION.add(Create.class);
    CRUD_ANNOTATION.add(Retrieve.class);
    CRUD_ANNOTATION.add(Update.class);
    CRUD_ANNOTATION.add(Delete.class);

    STRICT_ANNOTATION.add(PostMapping.class);
    STRICT_ANNOTATION.add(GetMapping.class);
    STRICT_ANNOTATION.add(PutMapping.class);
    STRICT_ANNOTATION.add(DeleteMapping.class);
  }

  private boolean strictMode;

  private static final Logger logger = LoggerFactory.getLogger(ResourceAspect.class);

  public ResourceAspect(boolean strictMode){
    this.strictMode = strictMode;
  }

  @Pointcut("@annotation(com.uhasoft.guard.annotation.Create)" +
      "|| @annotation(com.uhasoft.guard.annotation.Retrieve)" +
      "|| @annotation(com.uhasoft.guard.annotation.Update)" +
      "|| @annotation(com.uhasoft.guard.annotation.Delete)" +
      "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
      "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
      "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
      "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
  public void resourcePointcut(){
    logger.info("Nothing needed here.");
  }

  @Around("resourcePointcut()")
  public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
    Class<?> targetClass = joinPoint.getTarget().getClass();
    Method method = resolveMethod(joinPoint);
    String resource = resolveResource(method, targetClass);
    String type = resolvePermission(method);
    UserThreadLocal.setPermissionType(type);
    UserThreadLocal.setResource(resource);

    return joinPoint.proceed();
  }

  private String resolveResource(Method method, Class<?> targetClass){
    GuardResource resource = method.getDeclaredAnnotation(GuardResource.class);
    if(resource == null){
      resource = targetClass.getDeclaredAnnotation(GuardResource.class);
    }
    return resource != null ? resource.value() : null;
  }

  private Method resolveMethod(ProceedingJoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature)joinPoint.getSignature();
    Class<?> targetClass = joinPoint.getTarget().getClass();

    Method method = getDeclaredMethodFor(targetClass, signature.getName(),
        signature.getMethod().getParameterTypes());
    if (method == null) {
      logger.error("Cannot resolve target method: {}", signature.getMethod().getName());
      throw new IllegalStateException("Cannot resolve target method: " + signature.getMethod().getName());
    }
    return method;
  }

  private String resolvePermission(Method method){
    String permission = getPermission(CRUD_ANNOTATION, method);
    if(permission == null){//自定义PermissionType
      PermissionType permissionType = method.getDeclaredAnnotation(PermissionType.class);
      permission = permissionType == null ? null : permissionType.value();
    }
    if(permission == null && strictMode){
      permission = getPermission(STRICT_ANNOTATION, method);
    }
    return permission;
  }

  private String getPermission(List<Class<? extends Annotation>> annotationClasses, Method method){
    for(Class<? extends Annotation> clazz : annotationClasses){
      Annotation annotation = method.getDeclaredAnnotation(clazz);
      if(annotation != null){
        return PERMISSION_TYPE_MAP.get(clazz);
      }
    }
    return null;
  }

  /**
   * Get declared method with provided name and parameterTypes in given class and its super classes.
   * All parameters should be valid.
   *
   * @param clazz          class where the method is located
   * @param name           method name
   * @param parameterTypes method parameter type list
   * @return resolved method, null if not found
   */
  private Method getDeclaredMethodFor(Class<?> clazz, String name, Class<?>... parameterTypes) {
    try {
      return clazz.getDeclaredMethod(name, parameterTypes);
    } catch (NoSuchMethodException e) {
      Class<?> superClass = clazz.getSuperclass();
      if (superClass != null) {
        return getDeclaredMethodFor(superClass, name, parameterTypes);
      }
    }
    return null;
  }
}
