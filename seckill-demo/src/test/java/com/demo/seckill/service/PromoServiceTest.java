package com.demo.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by liyun on 2019/5/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PromoServiceTest {

    @Autowired
    private PromoService promoService;
    @Test
    public void getPromoByItemId() {
        System.out.println(promoService.getPromoByItemId(5));
    }
}