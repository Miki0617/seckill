package com.demo.seckill.dao;

import com.demo.seckill.domain.ItemDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ItemDAO {
    int deleteByPrimaryKey(Integer itemId);

    int insert(ItemDO record);

    int insertSelective(ItemDO record);

    ItemDO selectByPrimaryKey(Integer itemId);

    List<ItemDO> selectItemList();

    int updateByPrimaryKeySelective(ItemDO record);

    int updateByPrimaryKey(ItemDO record);
    //加销量
    int increaseSale(@Param("itemId") Integer itemId, @Param("amount") Integer amount);
}