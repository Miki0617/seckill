package com.demo.seckill.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 秒杀成功对象
 */
@Data
@NoArgsConstructor
public class SuccessKilledDO {
    private Long seckillId;
    /**
     * 用户名
     */
    private Long userId;
    /**
     * 秒杀状态
     * -1：无效   0：成功
     */
    private Byte state;
    /**
     * 秒杀创建时间
     */
    private Date createTime;
//    /**
//     * 秒杀活动对象
//     * 多条秒杀成功记录对应一个秒杀活动
//     */
//    private SeckillDO seckillDO;
}

