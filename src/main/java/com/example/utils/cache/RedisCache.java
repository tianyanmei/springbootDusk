package com.example.utils.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: Administrator
 * @date: 2019-06-25 10:47
 */

@Configuration
@EnableCaching
public class RedisCache extends CachingConfigurerSupport {

    private Logger logger = LoggerFactory.getLogger(RedisCache.class);

    @Bean
    public KeyGenerator keyGenerator() {
         return  new KeyGenerator() {
             @Override
             public Object generate(Object o, Method method, Object... objects) {
                  StringBuffer sb = new StringBuffer();
                  sb.append(o.getClass().getName());
                  sb.append(method.getName());
                  for (Object object:objects){
                      sb.append(object.toString());
                  }
                 logger.info("----------------"+sb.toString()+"----------------");
                 return sb.toString();
             }
         };
    }

}
