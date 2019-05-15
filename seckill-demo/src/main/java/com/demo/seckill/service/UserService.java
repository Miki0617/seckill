package com.demo.seckill.service;

import com.demo.seckill.dto.UserDTO;

/**
 * 用户Service层接口
 * Created by liyun on 2019/3/22
 */
public interface UserService {
    /**
     * 根据id查找用户
     * @param userId
     * @return
     */
    UserDTO getUserById(Long userId);

    /**
     * 用户注册
     * @param userDTO
     * @return
     */
    Boolean register(UserDTO userDTO);

    UserDTO login(String telphone, String passwd);
}
