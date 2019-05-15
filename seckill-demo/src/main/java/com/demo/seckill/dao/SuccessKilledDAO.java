package com.demo.seckill.dao;

import com.demo.seckill.domain.SuccessKilledDO;

public interface SuccessKilledDAO {
    int deleteByPrimaryKey(Long seckillId, Long userId);

    /**
     * 插入购买记录，可过滤重复
     * @param seckillId
     * @param userId
     * @return
     */
    int insertByPrimaryKey(Long seckillId, Long userId);

    int insertSelective(SuccessKilledDO record);
    /**
     * 根据seckillId查询并携带秒杀活动实体
     * @param seckillId
     * @return
     */
    SuccessKilledDO selectBySeckillIdWithSeckill(Long seckillId, Long userId);

    int updateByPrimaryKeySelective(SuccessKilledDO record);

    int updateByPrimaryKey(SuccessKilledDO record);
}