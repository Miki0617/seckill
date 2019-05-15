package com.demo.seckill.domain;

import lombok.Data;

import java.util.Date;
@Data
public class PromoDO {
    private Integer promoId;

    private String promoName;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private Integer itemId;

    private Double promoPrice;

}