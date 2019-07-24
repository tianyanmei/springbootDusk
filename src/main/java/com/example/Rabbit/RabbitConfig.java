package com.example.Rabbit;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Administrator
 * @date: 2019-07-05 14:27
 */

@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue(){
        return  new Queue("hello");
    }

}
