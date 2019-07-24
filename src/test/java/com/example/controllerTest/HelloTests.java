package com.example.controllerTest;

import com.example.Rabbit.HelloReceiver;
import com.example.Rabbit.HelloSender;
import com.example.controller.oneController.HelloWorldController;
import com.example.model.mybatis.UserMapper;
import com.example.model.pojo.User;
import com.example.service.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @description:
 * @author: Administrator
 * @date: 2019-06-24 10:17
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloTests {

    private MockMvc mockMvc;

    @Before
    public  void  setUp(){
        mockMvc=MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
                //mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    }


    @Test
    public  void  getHello() throws  Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hello/helloworld").
                accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

       // mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
             //   .andExpect(status().isOk())
               // .andExpect(content().string(equalTo("Hello World")));

    }


    @Autowired
    private UserRepository userRepository;

    @Test
    public void  testUserRepository() throws Exception {

        LocalDateTime localDateTime =LocalDateTime.now();
        String formattedDate =
                localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        userRepository.save(new User("dd1", "dd@126.com", "dd", "dd123456",formattedDate));


       /*



        Assert.assertEquals(9, userRepository.findAll().size());
        Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa1"));*/

    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public  void testRedis() throws  Exception{
        stringRedisTemplate.opsForValue().set("aaa","111");
        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));

        LocalDateTime localDateTime =LocalDateTime.now();
        String formattedDate =
                localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        User user =
        new User("ee1", "ee@126.com", "ee", "ee123456",formattedDate);
        ValueOperations<String,User>  operations = redisTemplate.opsForValue();
        operations.set("com.user1",user);
        operations.set("com.user2",user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);

        boolean exists= redisTemplate.hasKey("com.user2");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }

    /**
     * Mybatis注解测试
     */

    @Autowired
    UserMapper userMapper;

    @Test
    public  void  testMybatisScan(){
        List<Map<Object,Object>> list =userMapper.getAll();
        System.out.println(list);
        long id=1;
        System.out.println(userMapper.getOne(id));


    }


    //rabbit 队列测试
    @Autowired
    private HelloSender helloSender;


    @Test
    public  void  testHelloSender()throws  Exception{
        helloSender.send();
    }





}
