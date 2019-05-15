package com.demo.seckill.domain;

import lombok.Data;

@Data
public class ItemDO {
    private Integer itemId;

    private String title;

    private Double price;

    private String detail;

    private Integer sale;

    private String imgUrl;

}