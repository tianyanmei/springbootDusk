package com.example.Rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Administrator
 * @date: 2019-07-05 14:36
 */

@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public  void  process(String hello){
        System.out.println("receiver:"+hello);
    }

}
