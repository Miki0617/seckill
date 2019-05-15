package com.demo.seckill.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 商品模型
 * Created by liyun on 2019/5/7
 */
@Data
@NoArgsConstructor
public class ItemDTO {
    //商品id
    private Integer itemId;
    //商品标题
    @NotBlank(message = "商品标题不能为空")
    private String title;
    //商品描述详情
    @NotBlank(message = "商品详情不能为空")
    private String detail;
    //商品图片链接
    @NotBlank(message = "商品图片链接不能为空")
    private String imgUrl;
    //商品价格
    @NotNull(message = "价格不能为空")
    private BigDecimal price;
    //商品库存
    @NotNull(message = "库存不能为空")
    private Integer stock;
    //商品销量
    private Integer sale;
    //聚合秒杀活动模型
    private PromoDTO promoDTO;

    public ItemDTO(String title, String detail, String imgUrl, BigDecimal price, Integer stock) {
        this.title = title;
        this.detail = detail;
        this.imgUrl = imgUrl;
        this.price = price;
        this.stock = stock;
        this.sale = 0;
    }

}
