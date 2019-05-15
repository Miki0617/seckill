package com.demo.seckill.service.util;

import com.demo.seckill.domain.OrderDO;
import com.demo.seckill.dto.OrderDTO;
import org.springframework.beans.BeanUtils;


/**
 * Created by liyun on 2019/5/13
 */
public class OrderServiceUtils {

    public static OrderDO convertOrderDOFromOrderDTO(OrderDTO orderDTO){
        if(orderDTO == null)
            return null;
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderDTO,orderDO);
        orderDO.setItemPrice(orderDTO.getItemPrice().doubleValue());
        orderDO.setOrderPrice(orderDTO.getOrderPrice().doubleValue());
        return orderDO;
    }

}
