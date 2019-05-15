package com.demo.seckill.controller;

import com.demo.seckill.dto.ItemDTO;
import com.demo.seckill.response.CommonReturnType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liyun on 2019/5/7
 */
@RequestMapping("/item")
public interface ItemController {

    /**
     * 创建商品
     * @param itemDTO
     * @return
     */
    @PostMapping(value = "/create")
    CommonReturnType createItem(@RequestBody ItemDTO itemDTO);

    @GetMapping(value = "/get")
    CommonReturnType getItemById(@RequestParam(name = "id") Integer id);

    @GetMapping(value = "/list")
    CommonReturnType getItemList();
}
