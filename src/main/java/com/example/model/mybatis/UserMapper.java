package com.example.model.mybatis;

import com.example.model.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Administrator
 * @date: 2019-06-25 15:58
 */

public interface UserMapper {
    @Select("select * from user")
    List<Map<Object,Object>> getAll();

     @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "userName",column ="user_name"),
            @Result(property = "nickName",column = "nick_name" ),
            @Result(property = "regTime",column = "reg_time")
    })
     User getOne(Long id);


   @Insert("INSERT INTO user(`id`, `email`, `nick_name`, `password`, `reg_time`, `user_name`) VALUES(#{id},#{userName},#{password},#{email},#{nickName},#{regTime})")
    void insert(User user);

    @Update("UPDATE user SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);

}
