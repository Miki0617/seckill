package com.demo.seckill.controller.impl;

import com.demo.seckill.controller.ItemController;
import com.demo.seckill.controller.util.ItemControllerUtils;
import com.demo.seckill.dto.ItemDTO;
import com.demo.seckill.response.CommonReturnType;
import com.demo.seckill.service.ItemService;
import com.demo.seckill.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liyun on 2019/5/7
 */
@RestController("item")
//满足ajax跨域
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = "*")
public class ItemControllerImpl implements ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 创建商品
     * @param itemDTO
     * @return
     */
    @Override
    public CommonReturnType createItem(@Valid ItemDTO itemDTO) {
        ItemDTO itemReturnDTO = itemService.createItem(itemDTO);
        ItemVO itemVO = ItemControllerUtils.convertItemVOFromDTO(itemReturnDTO);
        return new CommonReturnType(itemVO);
    }

    @Override
    public CommonReturnType getItemById(Integer id) {
        ItemDTO itemDTO = itemService.getItemById(id);
        ItemVO itemVO = ItemControllerUtils.convertItemVOFromDTO(itemDTO);

        return new CommonReturnType(itemVO);
    }

    @Override
    public CommonReturnType getItemList() {
        List<ItemDTO> itemDTOList = itemService.getItemList();
        //将List中的ItemDTO转换为ItemVO
        List<ItemVO> itemVOList = itemDTOList.stream().map(itemDTO -> {
            ItemVO itemVO = ItemControllerUtils.convertItemVOFromDTO(itemDTO);

            return itemVO;
        }).collect(Collectors.toList());
        return new CommonReturnType(itemVOList);
    }
}
