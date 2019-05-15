package com.demo.seckill.service.impl;

import com.demo.seckill.dao.ItemDAO;
import com.demo.seckill.dao.ItemStockDAO;
import com.demo.seckill.domain.ItemDO;
import com.demo.seckill.domain.ItemStockDO;
import com.demo.seckill.dto.ItemDTO;
import com.demo.seckill.dto.PromoDTO;
import com.demo.seckill.enums.BusinessErrorEnum;
import com.demo.seckill.exception.BusinessException;
import com.demo.seckill.service.ItemService;
import com.demo.seckill.service.PromoService;
import com.demo.seckill.service.util.ItemServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品Service层接口实现
 * Created by liyun on 2019/5/7
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private ItemStockDAO itemStockDAO;

    @Autowired
    private PromoService promoService;

    @Override
    @Transactional
    public ItemDTO createItem(@Valid ItemDTO itemDTO) {
        if(itemDTO == null)
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_EMPTY);
        //转化
        ItemDO itemDO = ItemServiceUtils.convertItemDOFromItemDTO(itemDTO);
        //写入item表
        itemDAO.insertSelective(itemDO);
        itemDTO.setItemId(itemDO.getItemId());
        //写入item_stock表
        ItemStockDO itemStockDO = ItemServiceUtils.convertItemStockDOFromItemDTO(itemDTO);
        itemStockDAO.insertSelective(itemStockDO);
        //返回创建完成的对象
        return this.getItemById(itemDTO.getItemId());
    }

    @Override
    public ItemDTO getItemById(Integer itemId) {
        ItemDO itemDO = itemDAO.selectByPrimaryKey(itemId);
        if(itemDO == null)
            return null;
        //获得库存数量
        ItemStockDO itemStockDO = itemStockDAO.selectByItemId(itemDO.getItemId());
        ItemDTO itemDTO = ItemServiceUtils.convertItemDTOFromDO(itemDO, itemStockDO);
        //获得秒杀活动信息
        PromoDTO promoDTO = promoService.getPromoByItemId(itemId);
        //秒杀活动存在并且未结束
        if(promoDTO != null && promoDTO.getStatus() != 3)
            itemDTO.setPromoDTO(promoDTO);

        return itemDTO;
    }

    @Override
    public List<ItemDTO> getItemList() {
        List<ItemDO> itemDOList = itemDAO.selectItemList();
        //List中的itemDO转换为itemDTO
        //很优雅
        List<ItemDTO> itemDTOList = itemDOList.stream().map(itemDO -> {
            ItemStockDO itemStockDO = itemStockDAO.selectByItemId(itemDO.getItemId());
            ItemDTO itemDTO = ItemServiceUtils.convertItemDTOFromDO(itemDO, itemStockDO);

            return itemDTO;
        }).collect(Collectors.toList());

        return itemDTOList;
    }

    @Override
    @Transactional
    public Boolean decreaseStock(Integer itemId, Integer amount) {
        int affectRow = itemStockDAO.decreaseStock(itemId, amount);
        //更新库存成功
        if(affectRow > 0)
            return true;
        //更新库存失败
        else
            return false;
    }

    @Override
    @Transactional
    public Boolean increaseSale(Integer itemId, Integer amount) {
        int affectRow = itemDAO.increaseSale(itemId, amount);
        //更新销量成功
        if(affectRow > 0)
            return true;
            //更新库存失败
        else
            return false;
    }
}
