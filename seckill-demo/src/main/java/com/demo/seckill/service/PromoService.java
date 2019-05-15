package com.demo.seckill.service;

import com.demo.seckill.dto.PromoDTO;

/**
 * Created by liyun on 2019/5/15
 */
public interface PromoService {

    /**
     * 获取商品秒杀信息
     * @param itemId
     * @return
     */
    PromoDTO getPromoByItemId(Integer itemId);

    //TODO 创建秒杀信息
    //TODO 删除秒杀信息
}
