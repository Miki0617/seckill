package com.demo.seckill.service.util;

import com.demo.seckill.domain.ItemDO;
import com.demo.seckill.domain.ItemStockDO;
import com.demo.seckill.dto.ItemDTO;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * ItemService工具类
 * Created by liyun on 2019/5/7
 */
public class ItemServiceUtils {

    public static ItemDO convertItemDOFromItemDTO(ItemDTO itemDTO){
        if(itemDTO == null)
            return null;
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemDTO,itemDO);
        itemDO.setPrice(itemDTO.getPrice().doubleValue());

        return itemDO;
    }

    public static ItemStockDO convertItemStockDOFromItemDTO(ItemDTO itemDTO){
        if(itemDTO == null)
            return null;
        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setItemId(itemDTO.getItemId());
        itemStockDO.setStock(itemDTO.getStock());

        return itemStockDO;
    }

    public static ItemDTO convertItemDTOFromDO(ItemDO itemDO, ItemStockDO itemStockDO){
        if(itemDO == null || itemStockDO == null)
            return null;
        ItemDTO itemDTO = new ItemDTO();
        BeanUtils.copyProperties(itemDO,itemDTO);
        itemDTO.setPrice(new BigDecimal(itemDO.getPrice()));
        itemDTO.setStock(itemStockDO.getStock());

        return itemDTO;
    }
}
