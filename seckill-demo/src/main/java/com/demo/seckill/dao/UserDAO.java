package com.demo.seckill.dao;

import com.demo.seckill.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

public interface UserDAO {
    int deleteByPrimaryKey(Long userId);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Long userId);

    UserDO selectByTelphone(String telphone);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}