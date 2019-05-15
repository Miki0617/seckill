package com.demo.seckill.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * 用户模型
 * Created by liyun on 2019/3/22
 */
@Data
public class UserDTO {
    //用户id
    private Long userId;
    @NotBlank(message = "用户名不能为空")
    //用户名
    private String name;
    //性别
    @NotNull(message = "性别不能为空")
    private Byte gender;
    //年龄
    //@Size(max = 150,message = "年龄需要在0-150岁之间")
    @NotNull(message = "年龄不能为空")
    private Short age;
    //手机号
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^\\d{11}$",message = "手机号不合法")
    private String telphone;
    //密码
    @Length(min = 6,message = "密码至少为6位")
    private String encrptPasswd;
    //注册方式
    private String registerMode;
    //第三方账号
    private String thirdPartyId;
}
