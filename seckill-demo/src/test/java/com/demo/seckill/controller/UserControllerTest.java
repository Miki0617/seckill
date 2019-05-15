package com.demo.seckill.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by liyun on 2019/4/29
 */
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void getOtp() {
    }

    @Test
    public void getUserById() {
        Long id = 1L;
        userController.getUserById(id);
    }
}