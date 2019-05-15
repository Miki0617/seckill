package com.demo.seckill.service;

import com.demo.seckill.dto.ItemDTO;

import java.util.List;

/**
 * 商品Service层接口
 * Created by liyun on 2019/5/7
 */
public interface ItemService {
    /**
     * 创建商品
     * @param itemDTO
     * @return
     */
    ItemDTO createItem(ItemDTO itemDTO);
    /**
     * 通过id获取单个商品
     * @param itemId
     * @return
     */
    ItemDTO getItemById(Integer itemId);
    /**
     * 获取商品列表
     * @return
     */
    List<ItemDTO> getItemList();
    /**
     * 减库存
     * @param itemId
     * @param amount
     * @return
     */
    Boolean decreaseStock(Integer itemId, Integer amount);
    /**
     * 加销量
     * @param itemId
     * @param amount
     * @return
     */
    Boolean increaseSale(Integer itemId, Integer amount);
}
