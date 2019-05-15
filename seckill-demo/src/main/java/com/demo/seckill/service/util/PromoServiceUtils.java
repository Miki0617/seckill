package com.demo.seckill.service.util;

import com.demo.seckill.domain.PromoDO;
import com.demo.seckill.dto.PromoDTO;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * Created by liyun on 2019/5/15
 */
public class PromoServiceUtils {
    public static PromoDTO convertPromoDTOFromDO(PromoDO promoDO){
        if(promoDO == null)
            return null;
        PromoDTO promoDTO = new PromoDTO();
        BeanUtils.copyProperties(promoDO,promoDTO);
        promoDTO.setPromoPrice(new BigDecimal(promoDO.getPromoPrice()));

        return promoDTO;
    }
}
