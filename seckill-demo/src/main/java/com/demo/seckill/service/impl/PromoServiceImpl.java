package com.demo.seckill.service.impl;

import com.demo.seckill.dao.PromoDAO;
import com.demo.seckill.domain.PromoDO;
import com.demo.seckill.dto.PromoDTO;
import com.demo.seckill.service.PromoService;
import com.demo.seckill.service.util.PromoServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by liyun on 2019/5/15
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDAO promoDAO;

    @Override
    public PromoDTO getPromoByItemId(Integer itemId) {
        PromoDO promoDO = promoDAO.selectByItemId(itemId);
        if(promoDO == null)
            return null;
        PromoDTO promoDTO = PromoServiceUtils.convertPromoDTOFromDO(promoDO);
        //活动开始时间和结束时间与当前时间比较来决定活动状态
        //秒杀活动状态 1未开始 2进行中 3已结束
        Date currentTime = new Date();
        //秒杀未开始
        if(promoDTO.getStartTime().compareTo(currentTime) == 1)
            promoDTO.setStatus(1);
        //秒杀已结束
        else if(promoDTO.getEndTime().compareTo(currentTime) == -1)
            promoDTO.setStatus(3);
        //秒杀进行中
        else
            promoDTO.setStatus(2);

        return promoDTO;
    }

//    public static void main(String[] args) {
//        Date currentTime = new Date();
//        System.out.println(currentTime);
//    }
}

