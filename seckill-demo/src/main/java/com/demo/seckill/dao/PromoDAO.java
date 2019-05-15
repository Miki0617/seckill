package com.demo.seckill.dao;

import com.demo.seckill.domain.PromoDO;

public interface PromoDAO {
    int deleteByPrimaryKey(Integer promoId);

    int insert(PromoDO record);

    int insertSelective(PromoDO record);

    PromoDO selectByPrimaryKey(Integer promoId);

    PromoDO selectByItemId(Integer itemId);

    int updateByPrimaryKeySelective(PromoDO record);

    int updateByPrimaryKey(PromoDO record);
}