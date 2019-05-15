package com.demo.seckill.controller.util;

import com.demo.seckill.dto.UserDTO;
import com.demo.seckill.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by liyun on 2019/4/29
 */
@Slf4j
public class UserControllerUtils {
//    /**
//     * UserDTO标准化
//     * -> 密码MD5加密
//     * @param userDTO
//     */
//    public static void standardizeUserDTO(UserDTO userDTO){
////        //Integer转Byte
////        //Integer -> int -> String -> Byte
////        userDTO.setGender(new Byte(String.valueOf(userDTO.getGender().intValue())));
////        //Integer转Short
////        userDTO.setAge(new Short(String.valueOf(userDTO.getAge().intValue())));
//        userDTO.setEncrptPasswd(encodeByMD5(userDTO.getEncrptPasswd()));
//
//    }

    /**
     * 将DTO对象转化为VO对象
     * @param userDTO
     * @return
     */
    public static UserVO convertUserVOFromDTO(UserDTO userDTO){
        if(userDTO == null)
            return null;
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDTO,userVO);

        return userVO;
    }
}
