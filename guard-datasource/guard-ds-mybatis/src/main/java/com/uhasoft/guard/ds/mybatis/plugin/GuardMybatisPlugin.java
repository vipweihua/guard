package com.uhasoft.guard.ds.mybatis.plugin;

import com.uhasoft.guard.context.UserThreadLocal;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author Weihua
 * @since 1.0.0
 */
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class GuardMybatisPlugin implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    List<String> limitations = UserThreadLocal.getLimitation();
    if(!limitations.isEmpty()){
      MappedStatement statement = (MappedStatement)invocation.getArgs()[0];
      SqlSource sqlSource = statement.getSqlSource();
      if(sqlSource instanceof RawSqlSource){
        MetaObject msObject = SystemMetaObject.forObject(statement);
        msObject.setValue("sqlSource", new GuardSqlSource((RawSqlSource)sqlSource));
        return invocation.proceed();
      }
    }
    return invocation.proceed();
  }

  public Object plugin(Object target) {
    if(target instanceof Executor){
      return Plugin.wrap(target, this);
    }
    return target;
  }

}
