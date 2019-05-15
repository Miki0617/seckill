package com.demo.seckill.service;

import com.demo.seckill.dto.ItemDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by liyun on 2019/5/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void createItem() {
//        ItemDTO itemDTO = new ItemDTO("iphone6","好用的","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH3QwH99R3_wqU6HHONsl884bvZr7PUAHWuPy-AAauDwPegt_ITA", new BigDecimal(800),100);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setTitle("iphone89");
        itemService.createItem(itemDTO);
        assertNotNull(itemDTO.getItemId());
        System.out.println(itemDTO);
    }

    @Test
    public void getItemById() {
        Integer id = 5;
        ItemDTO itemDTO = itemService.getItemById(id);
        assertNotNull(itemDTO);
        System.out.println(itemDTO);
    }

    @Test
    public void getItemList() {
    }
}