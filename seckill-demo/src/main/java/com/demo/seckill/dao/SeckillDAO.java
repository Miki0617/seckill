package com.demo.seckill.dao;

import com.demo.seckill.domain.SeckillDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface SeckillDAO {
    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId") Long seckillId, @Param("killTime") Date killTime);

    int deleteByPrimaryKey(Long seckillId);

    int insert(SeckillDO record);

    int insertSelective(SeckillDO record);

    SeckillDO selectByPrimaryKey(Long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @return
     */
    List<SeckillDO> selectPart(@Param("offset") Integer offset, @Param("limit")Integer limit);

    List<SeckillDO> selectAll();

    int updateByPrimaryKeySelective(SeckillDO record);

    int updateByPrimaryKey(SeckillDO record);
}