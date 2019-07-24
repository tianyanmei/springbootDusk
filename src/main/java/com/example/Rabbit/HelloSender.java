package com.example.Rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: Administrator
 * @date: 2019-07-05 14:32
 */

@Component
public class HelloSender {


    @Autowired
    private AmqpTemplate rabbitTemplate;

    public  void  send(){
        String context = "hello"+ LocalDateTime.now();
        System.out.println("sender:"+context);
        this.rabbitTemplate.convertAndSend("hello",context);
    }



}
