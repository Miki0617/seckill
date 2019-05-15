package com.demo.seckill.dao;

import com.demo.seckill.domain.UserPasswdDO;
import org.apache.ibatis.annotations.Mapper;

public interface UserPasswdDAO {
    int deleteByPrimaryKey(Long userId);

    int insert(UserPasswdDO record);

    int insertSelective(UserPasswdDO record);

    UserPasswdDO selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserPasswdDO record);

    int updateByPrimaryKey(UserPasswdDO record);
}