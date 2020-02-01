package com.uhasoft.guard.ds.json.configuration;

import com.uhasoft.guard.ds.json.JsonReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Weihua
 * @since 1.0.0
 */
@Configuration
public class JsonAutoConfiguration {

  @Bean
  public JsonReader jsonReader(){
    return new JsonReader();
  }
}
