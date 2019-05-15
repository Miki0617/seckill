package com.demo.seckill.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 秒杀活动模型
 * Created by liyun on 2019/5/14
 */
@Data
public class PromoDTO {
    //秒杀活动id
    private Integer promoId;
    //秒杀活动状态 1未开始 2进行中 3已结束
    private Integer status;
    //秒杀活动名称
    private String promoName;
    //商品id
    private Integer itemId;
    //秒杀价格
    private BigDecimal promoPrice;
    //秒杀活动创建时间
    private Date createTime;
    //活动开始时间
    private Date startTime;
    //活动结束时间
    private Date endTime;
}
