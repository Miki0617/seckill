package com.demo.seckill.controller;

import com.demo.seckill.dto.UserDTO;
import com.demo.seckill.response.CommonReturnType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liyun on 2019/4/1
 */
@RequestMapping("/user")
public interface UserController {
    /**
     * 获取otp短信
     * @param telphone
     * @return
     */
    @PostMapping(value = "/getotp")
    CommonReturnType getOtp(@RequestParam(name="telphone")String telphone);

    /**
     * 通过ID查询用户
     * @param userId
     * @return
     */
    @GetMapping("/get")
    CommonReturnType getUserById(@RequestParam(name="id")Long userId);

    /**
     * 用户注册
     * @param otpCode
     * @param userDTO
     * @return
     */
//    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/register")
    CommonReturnType register(@RequestParam(name = "otpCode") String otpCode,
                              @RequestBody UserDTO userDTO);

    /**
     * 用户登录
     * @param telphone
     * @param passwd
     * @return
     */
    @PostMapping(value = "/login")
    CommonReturnType login(@RequestParam(name = "telphone") String telphone,
                           @RequestParam(name = "passwd") String passwd);
}
