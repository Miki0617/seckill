package com.demo.seckill.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.demo.seckill.dao.UserDAO;
import com.demo.seckill.dao.UserPasswdDAO;
import com.demo.seckill.domain.UserDO;
import com.demo.seckill.domain.UserPasswdDO;
import com.demo.seckill.enums.BusinessErrorEnum;
import com.demo.seckill.exception.BusinessException;
import com.demo.seckill.service.UserService;
import com.demo.seckill.dto.UserDTO;
import com.demo.seckill.service.util.UserServiceUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.beans.Transient;

/**
 * 用户Service层接口实现
 * Created by liyun on 2019/3/22
 */
@Service
@Validated
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserPasswdDAO userPasswdDAO;

    /**
     * 根据id查找用户
     * @param userId
     * @return
     */
    @Override
    public UserDTO getUserById(Long userId) {
        UserDO userDO = userDAO.selectByPrimaryKey(userId);
        UserPasswdDO userPasswdDO = userPasswdDAO.selectByPrimaryKey(userId);

        return UserServiceUtils.convertUserDTOFromDO(userDO,userPasswdDO);
    }

    /**
     * 用户注册
     * @param userDTO
     * @return
     */
    @Override
    @Transient
    public Boolean register(UserDTO userDTO) {
        if(userDTO == null)
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_EMPTY);
        UserDO userDO = UserServiceUtils.convertUserDOFromDTO(userDTO);
        //用户已存在
        if(userDAO.selectByTelphone(userDO.getTelphone()) != null)
            throw new BusinessException(BusinessErrorEnum.USER_IS_EXIST);
        //userDO插入失败或者没有获取到userId
        if(userDAO.insertSelective(userDO)<=0 || userDO.getUserId() == null)
            return false;
        userDTO.setUserId(userDO.getUserId());
        userDTO.setEncrptPasswd(UserServiceUtils.encodeByMD5(userDTO.getEncrptPasswd()));
        UserPasswdDO userPasswdDO = UserServiceUtils.convertUserPasswdDOFromDTO(userDTO);
        //user和user_passwd均插入成功后才会返回true
        return userPasswdDAO.insertSelective(userPasswdDO)>0;
    }

    /**
     * 用户登录
     * @param telphone
     * @param passwd
     * @return
     */
    @Override
    public UserDTO login(String telphone, String passwd) {
        UserDO userDO = userDAO.selectByTelphone(telphone);
        //用户不存在
        if(userDO == null)
            throw new BusinessException(BusinessErrorEnum.USER_LOGIN_FAIL);
        UserPasswdDO userPasswdDO = userPasswdDAO.selectByPrimaryKey(userDO.getUserId());
        //密码不正确（加密明文密码）
        if(!StringUtils.equals(userPasswdDO.getEncrptPasswd(),UserServiceUtils.encodeByMD5(passwd)))
            throw new BusinessException(BusinessErrorEnum.USER_LOGIN_FAIL);
        UserDTO userDTO = UserServiceUtils.convertUserDTOFromDO(userDO, userPasswdDO);

        return userDTO;
    }
}
