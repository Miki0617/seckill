package com.demo.seckill.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liyun on 2019/5/7
 */
@Data
public class ItemVO {
    //商品id
    private Integer itemId;
    //商品标题
    private String title;
    //商品描述详情
    private String detail;
    //商品图片链接
    private String imgUrl;
    //商品价格
    private BigDecimal price;
    //商品库存
    private Integer stock;
    //商品销量
    private Integer sale;

    //秒杀活动信息
    //秒杀活动状态 0没有活动 1未开始 2进行中 3已结束
    private Integer promoStatus;
    //秒杀活动价格
    private BigDecimal promoPrice;
    //秒杀活动id
    private Integer promoId;
    //秒杀开始时间
    //直接返回给前端Date格式无法正常显示
    private String startTime;
    //秒杀结束时间
    private String endTime;
}
