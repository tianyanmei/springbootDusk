package com.example.controller.oneController;

import com.example.service.UserRepository;
import com.example.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Administrator
 * @date: 2019-06-25 11:36
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/getUser")
    @Cacheable(value = "user-key")
    public User getUser(){
        User user=new User("aa@126.com", "aa", "aa123456", "aa","123");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }

    @RequestMapping(value = "/getUser/{id}")
    @Cacheable(value = "user-key")
    public User getUser(@PathVariable Long id){
        User user = userRepository.findByUserId(id);
        return user;
    }




}
