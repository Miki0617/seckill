package com.demo.seckill.service;

import com.demo.seckill.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by liyun on 2019/3/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Test
    public void getUserById() {
        Long id = 1L;
        UserDTO userDTO = userService.getUserById(id);
        assertNotNull(userDTO);
        System.out.println(userDTO);
    }
}