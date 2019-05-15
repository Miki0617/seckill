package com.demo.seckill.controller.impl;

import com.demo.seckill.controller.OrderController;
import com.demo.seckill.dto.OrderDTO;
import com.demo.seckill.dto.UserDTO;
import com.demo.seckill.enums.BusinessErrorEnum;
import com.demo.seckill.exception.BusinessException;
import com.demo.seckill.response.CommonReturnType;
import com.demo.seckill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liyun on 2019/5/14
 */
@RestController("order")
//满足ajax跨域
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = "*")
public class OrderControllerImpl implements OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    HttpServletRequest httpServletRequest;
    /**
     * 创建订单
     * @param itemId
     * @param amount
     * @return
     */
    @Override
    public CommonReturnType createOrder(Integer itemId, Integer promoId, Integer amount) {
        //验证用户是否登录
        Boolean isLogin = (Boolean)httpServletRequest.getSession().getAttribute("LOGIN");
        if(isLogin == null || !isLogin.booleanValue())
            throw new BusinessException(BusinessErrorEnum.USER_NOT_LOGIN);
        //获取登录用户信息
        UserDTO userDTO = (UserDTO)httpServletRequest.getSession().getAttribute("LOGIN_USER");
        OrderDTO orderDTO = orderService.createOrder(userDTO.getUserId(), itemId, promoId, amount);
        return new CommonReturnType(null);
    }
}
