package com.lhf.library.mapper;

import com.lhf.library.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author lihuifeng
 * @Create 2023/5/29 16:35
 */
@SpringBootTest
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    void insertUser(){
        User user = new User();
        user.setName("张三");
        user.setPassword("asdas");
        System.out.println(userMapper.insertUser(user));
    }
    @Test
    void selectUser(){
        System.out.println(userMapper.selectUser("123", "1"));
    }

    @Test
    void selectUserByIdTest(){
        System.out.println(userMapper.selectUserById(1));
    }

    @Test
    void updateUserMoneyTest(){
        System.out.println(userMapper.updateUserMoney(1000.00, "123"));
    }
}