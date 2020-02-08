package com.uhasoft.guard.ds.mybatis.plugin;

import com.uhasoft.guard.context.UserThreadLocal;
import com.uhasoft.guard.entity.Limitation;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.RawSqlSource;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 @author Weihua
 @since 1.0.0 */
public class GuardSqlSource implements SqlSource {

  private static final Logger logger = LoggerFactory.getLogger(GuardSqlSource.class);
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
    try {
      Statement statement = CCJSqlParserUtil.parse(sql);
      if(statement instanceof Select){
        Select select = (Select)statement;
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        Expression where = plainSelect.getWhere();
        List<Limitation> limitations = UserThreadLocal.getLimitation();
        Expression expression = null;
        for (Limitation limitation : limitations) {
          EqualsTo equalsTo = new EqualsTo();
          equalsTo.setLeftExpression(new Column(limitation.getLeft()));
          equalsTo.setRightExpression(new StringValue(limitation.getRight()));
          if(expression == null){
            expression = equalsTo;
          } else {
            expression = new OrExpression(expression, equalsTo);
          }
        }
        if(where == null){
          plainSelect.setWhere(expression);
        } else {
          String whereString = "(" + where.toString() + ")";
          Expression newWhere = CCJSqlParserUtil.parseCondExpression(whereString);
          plainSelect.setWhere(new AndExpression(newWhere, expression));
        }
        logger.info("SQL:{}", statement.toString());
        return new BoundSql(configuration, statement.toString(), parameterMappings, parameterObject);
      }
    } catch (JSQLParserException e) {
      logger.error(e.getMessage(), e);
    }
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
    List<Limitation> limitations = UserThreadLocal.getLimitation();
    if(!limitations.isEmpty()){
      String expression = String.join(" or ", limitations.toString());
      return sql + " where " + expression;
    }
    return sql;
  }

}
