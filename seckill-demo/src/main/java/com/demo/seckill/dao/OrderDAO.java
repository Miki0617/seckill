package com.demo.seckill.dao;

import com.demo.seckill.domain.OrderDO;

public interface OrderDAO {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}