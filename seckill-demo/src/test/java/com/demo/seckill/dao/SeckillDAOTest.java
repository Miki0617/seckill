package com.demo.seckill.dao;

import com.demo.seckill.domain.SeckillDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by liyun on 2019/3/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SeckillDAOTest {

    @Autowired
    private SeckillDAO seckillDAO;

    @Test
    public void reduceNumber() {
        Long id = 1000L;
        Date killTime = new Date();
        System.out.println(killTime);
        int count = seckillDAO.reduceNumber(id, killTime);
        assertNotNull(count);
        System.out.println(count);

    }

    @Test
    public void selectByPrimaryKey() {
        Long id = 1001L;
        SeckillDO seckillDO = seckillDAO.selectByPrimaryKey(id);
        assertNotNull(seckillDO);
        System.out.println(seckillDO);
    }

    @Test
    public void selectAll() {
        List<SeckillDO> seckillDOList = seckillDAO.selectAll();
        assertNotNull(seckillDOList);
        for(SeckillDO seckillDO: seckillDOList){
            System.out.println(seckillDO.toString());
        }
    }
}