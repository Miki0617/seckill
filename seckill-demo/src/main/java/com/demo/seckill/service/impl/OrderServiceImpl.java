package com.demo.seckill.service.impl;

import com.demo.seckill.dao.OrderDAO;
import com.demo.seckill.dao.SequenceDAO;
import com.demo.seckill.domain.OrderDO;
import com.demo.seckill.domain.SequenceDO;
import com.demo.seckill.dto.ItemDTO;
import com.demo.seckill.dto.OrderDTO;
import com.demo.seckill.dto.UserDTO;
import com.demo.seckill.enums.BusinessErrorEnum;
import com.demo.seckill.exception.BusinessException;
import com.demo.seckill.service.ItemService;
import com.demo.seckill.service.OrderService;
import com.demo.seckill.service.UserService;
import com.demo.seckill.service.util.OrderServiceUtils;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 订单Service层接口实现
 * Created by liyun on 2019/5/13
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private SequenceDAO sequenceDAO;

    /**
     * 创建订单
     * @param userId
     * @param itemId
     * @param amount
     * @return
     */
    @Override
    @Transactional
    public OrderDTO createOrder(Long userId, Integer itemId, Integer promoId, Integer amount) {
        //1.参数校验
        ItemDTO itemDTO = itemService.getItemById(itemId);
        UserDTO userDTO = userService.getUserById(userId);
        //商品是否存在
        if(itemDTO == null)
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_EMPTY,"商品不存在");
        //用户是否合法
        if(userDTO == null)
            throw new BusinessException(BusinessErrorEnum.USER_NOT_EXIST);
        //购买数量是否正确
        if(amount <= 0 || amount >100)
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,"购买数量有误");
        //活动是否正确
        if(promoId != null){
            //活动是否对应适用商品
            if(promoId.intValue() != itemDTO.getPromoDTO().getPromoId()){
                throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,"活动信息不正确");
            }
            //活动是否在进行中
            else if(itemDTO.getPromoDTO().getStatus() != 2){
                throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,"活动不存在或未开始");
            }
        }
        //2.落单减库存   也有支付减库存
        //落单减库存用户体验较好
        Boolean result = itemService.decreaseStock(itemId, amount);
        //库存不足
        if(!result)
            throw new BusinessException(BusinessErrorEnum.STOCK_NOT_ENOUGH);
        //3.订单入库
        OrderDTO orderDTO = new OrderDTO();
        //初始化订单信息
        orderDTO.setUserId(userId);
        orderDTO.setItemId(itemId);
        orderDTO.setAmount(amount);
        //如果有秒杀活动，则以秒杀价格下单
        //TODO 代码逻辑还可以优化
        if(promoId != null){
            orderDTO.setItemPrice(itemDTO.getPromoDTO().getPromoPrice());
        }
        else{
            orderDTO.setItemPrice(itemDTO.getPrice());
        }
        orderDTO.setOrderPrice(orderDTO.getItemPrice().multiply(new BigDecimal(amount)));

        //生成订单号
        orderDTO.setOrderId(createOrderId());
        //生成订单时间和状态
        orderDTO.setCreateTime(new Date());
        orderDTO.setState(new Byte("1"));
        //入库
        OrderDO orderDO = OrderServiceUtils.convertOrderDOFromOrderDTO(orderDTO);
        orderDAO.insertSelective(orderDO);
        //TODO 支付完成后才增加销量，如果未支付，则库存回复
        //增加销量
        itemService.increaseSale(itemId,amount);

        return orderDTO;
    }

    @Override
    public OrderDTO cancelOrder(String orderId) {
        return null;
    }

    /**
     * 订单号生成
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    //TODO @Transactional只能应用在public上
    private String createOrderId(){

        //订单号有16位

        //TODO 年后两位-月-日-时-分-秒+用户id
        StringBuffer stringBuffer = new StringBuffer();
        //前6位为时间信息  年-月-日
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-","").substring(2);
        stringBuffer.append(nowDate);
        //中间6位为自增序列
        int sequence = 0;
        SequenceDO sequenceDO = sequenceDAO.selectByName("order_number");
        sequence = sequenceDO.getCurrentValue();
        if(sequence + sequenceDO.getStep() > sequenceDO.getMaxValue())
            sequenceDO.setCurrentValue(sequenceDO.getInitValue());
        else
            sequenceDO.setCurrentValue(sequence + sequenceDO.getStep());
        sequenceDAO.updateByPrimaryKeySelective(sequenceDO);
        String sequenceString = String.valueOf(sequence);
        for (int i = 0; i < 6-sequenceString.length(); i++) {
            stringBuffer.append(0);
        }
        stringBuffer.append(sequenceString);
        //最后2位为分库分表位
        //单机暂时写死
        stringBuffer.append("00");
        return stringBuffer.toString();
    }
}
