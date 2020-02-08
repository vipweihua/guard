package com.uhasoft.guard.demo.mybatis.configuration;

import com.mysql.jdbc.Driver;
import com.uhasoft.guard.demo.mybatis.mapper.OrderMapper;
import com.uhasoft.guard.ds.mybatis.plugin.GuardMybatisPlugin;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * @author Weihua
 * @since 1.0.0
 */
@Configuration
public class MybatisAutoConfiguration {

  @Bean
  public ConfigurationCustomizer mybatisConfigurationCustomizer() {
    return configuration -> {
      configuration.addMapper(OrderMapper.class);
      configuration.addInterceptor(new GuardMybatisPlugin());
//      configuration.addInterceptor(new PageHelper());
    };
  }

  @Bean
  public DataSource dataSource(){
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
    dataSource.setDriverClass(Driver.class);
    dataSource.setUsername("root");
    dataSource.setPassword("letmein");
    dataSource.setUrl("jdbc:mysql://localhost:3306/guard-demo?autoReconnect=true&useUnicode=true&characterEncoding=utf-8");
    return dataSource;
  }

}
