package com.uhasoft.guard.ds.mybatis.plugin;

import com.uhasoft.guard.context.UserThreadLocal;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 @author Weihua
 @since 1.0.0 */
public class GuardSqlSource implements SqlSource {
  private SqlSource sqlSource;
  private String sql;
  private List<ParameterMapping> parameterMappings;
  private Configuration configuration;
  private SqlSource original;

  public GuardSqlSource(RawSqlSource rawSqlSource) {
    this.original = rawSqlSource;
    MetaObject metaObject = SystemMetaObject.forObject(rawSqlSource);
    StaticSqlSource staticSqlSource = (StaticSqlSource) metaObject.getValue("sqlSource");
    metaObject = SystemMetaObject.forObject(staticSqlSource);

    this.sql = (String) metaObject.getValue("sql");
    this.parameterMappings = (List<ParameterMapping>) metaObject.getValue("parameterMappings");
    this.configuration = (Configuration) metaObject.getValue("configuration");
    this.sqlSource = staticSqlSource;
  }

  @Override
  public BoundSql getBoundSql(Object parameterObject) {
    String tempSql = sql;
    tempSql = getPageSql(tempSql);
    return new BoundSql(configuration, tempSql, parameterMappings, parameterObject);
  }

  /**
   暂时以硬编码方式，将来需要分析sql语法树，精确插入权限表达式
   @param sql 原sql
   @return 加入权限的sql
   */
  public String getPageSql(String sql) {
    List<String> limitations = UserThreadLocal.getLimitation();
    if(!limitations.isEmpty()){
      String expression = String.join(" or ", limitations);
      return sql + " where " + expression;
    }
    return sql;
  }

}
