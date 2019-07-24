package com.example.controller.oneController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Administrator
 * @date: 2019-06-24 10:09
 */

@RestController
@RequestMapping(value = "/hello")
public class HelloWorldController {

    @RequestMapping(value = "/helloworld")
    public  String hello(){
        return "hello World !";
    }


     //http://localhost:8080/hello/hello2?id=2
    @RequestMapping(value="hello2")
    public String hello2(@RequestParam(value = "id") String id){
       return  id;
    }

}
