package com.demo.seckill.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by liyun on 2019/3/22
 */
@Data
public class UserVO {

    private Long userId;
    @NotBlank
    private String name;
    @NotBlank
    private Byte gender;
    @NotBlank
    private Short age;
    @NotBlank
    private String telphone;


}
