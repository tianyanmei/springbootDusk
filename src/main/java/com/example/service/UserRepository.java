package com.example.service;

import com.example.model.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: Administrator
 * @date: 2019-06-24 15:43
 */

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    List<User> findAll();

    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);

    @Query("select u from  User u where  u.id=?1")
    User findByUserId(Long id);

    @Modifying
    @Query("update User u set u.userName = ?1 where u.id = ?2")
    int modifyByIdAndUserId(String  userName, Long id);

   @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteByUserId(Long id);

    @Transactional(timeout = 10)
    @Query("select u from User u where u.email = ?1")
    User findByEmailAddress(String email);

}

