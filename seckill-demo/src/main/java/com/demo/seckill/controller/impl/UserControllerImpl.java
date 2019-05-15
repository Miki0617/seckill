package com.demo.seckill.controller.impl;

import com.alibaba.druid.util.StringUtils;
import com.demo.seckill.controller.util.UserControllerUtils;
import com.demo.seckill.enums.BusinessErrorEnum;
import com.demo.seckill.controller.UserController;
import com.demo.seckill.exception.BusinessException;
import com.demo.seckill.response.CommonReturnType;
import com.demo.seckill.service.UserService;
import com.demo.seckill.dto.UserDTO;
import com.demo.seckill.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Random;




/**
 * Created by liyun on 2019/3/22
 */
@RestController("user")
//满足ajax跨域
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*",origins = "*")
//@Validated
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 获取otp短信
     *
     * @param telphone
     * @return
     * @throws BusinessException
     */
    //TODO 将"application/x-www-form-urlencoded"转为Enum
    //consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
//    @Pattern(regexp = "^\\d{11}$",message = "手机号不合法")
//    @NotBlank(message = "手机号不能为空")
    @Override
//    @Validated
    public CommonReturnType getOtp(@NotBlank(message = "手机号不能为空") @RequestParam(name = "telphone") String telphone) {
//        if(telphone == null)
//            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_EMPTY,"手机号不能为空");
//        Boolean isTelphone = Pattern.matches("^\\d{11}$",telphone);
//        //手机号不合法
//        if(!isTelphone){
//            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR, "手机号不合法");
//        }
        //随机生成Otp验证码
        int otpLength = 6;
        int[] otpArray = new int[otpLength];
        Random random = new Random();
        for (int i = 0; i < otpLength; i++) {
            otpArray[i] = random.nextInt(10);
        }
        StringBuffer otpCodeBuffer = new StringBuffer();
        for (int i : otpArray) {
            otpCodeBuffer.append(i);
        }
        String otpCode = otpCodeBuffer.toString();
        //将Otp验证码与手机号绑定,使用httpsession绑定
        httpServletRequest.getSession().setAttribute(telphone, otpCode);

        //TODO 将Otp验证码发给目标手机号
        System.out.println("telphone = " + telphone + " & otp = " + otpCode);

        return new CommonReturnType(null);
    }

    /**
     * 用户注册
     * @param otpCode
     * @param userDTO
     * @return
     */
//    @RequestParam(name = "name") Integer name,
//    @RequestParam(name = "gender") Integer gender,
//    @RequestParam(name = "age") Integer age
//    @PostMapping(value = "/register")
    @Override
    public CommonReturnType register(@RequestParam(name = "otpCode") String otpCode,
                                     @Valid @RequestBody UserDTO userDTO) {
        // @RequestParam(name = "otpCode") String otpCode
        //用户名和otpCode校验
        String otpCodeInSession = (String) this.httpServletRequest.getSession().getAttribute(userDTO.getTelphone());
        if (!StringUtils.equals(otpCode, otpCodeInSession)) {
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR, "短信验证码不符合");
        }
        //完成用户注册流程
        //标准化UserDTO
//        UserControllerUtil.standardizeUserDTO(userDTO);
        //注册成功
        if(userService.register(userDTO))
            return new CommonReturnType(null);
        else
            return new CommonReturnType(false,null);
    }

    /**
     * 用户登录
     * @param telphone
     * @param passwd
     * @return
     */
    @Override
    //@NotBlank(message = "手机号不能为空") @NotBlank(message = "密码不能为空")
    public CommonReturnType login(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "passwd")String passwd) {
        //登录成功
        UserDTO userDTO = userService.login(telphone, passwd);
        this.httpServletRequest.getSession().setAttribute("LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userDTO);

        return new CommonReturnType(null);
    }

    /**
     * 获取用户对象,通过调用service服务并返回给前端
     *
     * @param userId
     * @return
     * @throws BusinessException
     */
    @Override
    public CommonReturnType getUserById(@RequestParam(name = "id") Long userId) throws BusinessException {

        UserDTO userDTO = userService.getUserById(userId);
        //用户不存在
        if (userDTO == null) {
            throw new BusinessException(BusinessErrorEnum.USER_NOT_EXIST);
//            userDTO.setEncrptPasswd("123");
        }
        UserVO userVO = UserControllerUtils.convertUserVOFromDTO(userDTO);
        return new CommonReturnType(userVO);
    }


}
