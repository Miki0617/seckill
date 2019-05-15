package com.demo.seckill.controller.util;

import com.demo.seckill.dto.ItemDTO;
import com.demo.seckill.dto.PromoDTO;
import com.demo.seckill.vo.ItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liyun on 2019/5/7
 */
public class ItemControllerUtils {

    /**
     * 将DTO对象转化为VO对象
     * @param itemDTO
     * @return
     */
    public static ItemVO convertItemVOFromDTO(ItemDTO itemDTO){
        if(itemDTO == null)
            return null;
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemDTO,itemVO);

        PromoDTO promoDTO = itemDTO.getPromoDTO();
        //秒杀活动状态 0没有活动 1未开始 2进行中 3已结束
        //如果存在未结束的秒杀活动
        if(promoDTO != null){
            //活动已结束
            if(promoDTO.getStatus() == 3)
                itemVO.setPromoStatus(3);
            else{
                itemVO.setPromoStatus(promoDTO.getStatus());
                itemVO.setPromoId(promoDTO.getPromoId());
                itemVO.setPromoPrice(promoDTO.getPromoPrice());
                //时间格式转化 Date -> String
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                itemVO.setStartTime(simpleDateFormat.format(promoDTO.getStartTime()));
                itemVO.setEndTime(simpleDateFormat.format(promoDTO.getEndTime()));
            }

        }
        //没有活动
        else
            itemVO.setPromoStatus(0);

        return itemVO;
    }
}
