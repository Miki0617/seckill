package com.demo.seckill.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单模型
 * Created by liyun on 2019/5/13
 */
@Data
public class OrderDTO {
    //订单号
    //xxxx-xx-xx+xxxxxxxx
    private String orderId;

    //用户id
    private Long userId;

    //商品id
    private Integer itemId;

    //秒杀活动id
    private Integer promoId;

    //购买数量
    private Integer amount;

    //购买时商品单价
    private BigDecimal itemPrice;

    //订单总价
    private BigDecimal orderPrice;
    //订单创建时间
    private Date createTime;
    //订单状态
    private Byte state;
    //TODO

    //创建时间
    //收货人/收货地址
    //发货人/发货状态

}
