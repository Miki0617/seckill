package com.demo.seckill.dao;

import com.demo.seckill.domain.PromoDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by liyun on 2019/5/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PromoDAOTest {
    @Autowired
    private PromoDAO promoDAO;

    @Test
    public void selectByItemId() {
        PromoDO promoDO = promoDAO.selectByItemId(3);
        assertNotNull(promoDO);
        System.out.println(promoDO);
    }
}