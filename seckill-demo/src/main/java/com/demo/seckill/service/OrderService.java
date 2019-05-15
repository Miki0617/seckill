package com.demo.seckill.service;

import com.demo.seckill.dto.OrderDTO;

/**
 * 订单Service层接口
 * Created by liyun on 2019/5/13
 */
public interface OrderService {
    /**
     * 创建订单
     * @param userId
     * @param itemId
     * @param amount
     * @return
     */
    OrderDTO createOrder(Long userId, Integer itemId, Integer promoId, Integer amount);
    //TODO
    OrderDTO cancelOrder(String orderId);
}
