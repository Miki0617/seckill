package com.demo.seckill.controller;

import com.demo.seckill.response.CommonReturnType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by liyun on 2019/5/14
 */
@RequestMapping("/order")
public interface OrderController {
    /**
     * 创建订单
     * @param itemId
     * @param amount
     * @return
     */
    @PostMapping(value = "/create")
    CommonReturnType createOrder(@RequestParam(name="itemId")Integer itemId,
                                        @RequestParam(name="promoId",required = false)Integer promoId,
                                        @RequestParam(name="amount")Integer amount);
}
