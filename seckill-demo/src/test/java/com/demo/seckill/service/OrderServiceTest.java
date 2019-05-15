package com.demo.seckill.service;

import com.demo.seckill.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by liyun on 2019/5/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Test
    public void createOrder() {
        OrderDTO orderDTO = orderService.createOrder(1L,3,null,1);
        assertNotNull(orderDTO);
        System.out.println(orderDTO);
    }
}