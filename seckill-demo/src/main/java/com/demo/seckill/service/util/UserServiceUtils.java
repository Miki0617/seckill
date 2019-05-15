package com.demo.seckill.service.util;

import com.demo.seckill.domain.UserDO;
import com.demo.seckill.domain.UserPasswdDO;
import com.demo.seckill.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * UserService工具类
 * Created by liyun on 2019/4/28
 */
@Slf4j
public class UserServiceUtils {

    /**
     * 密码MD5加密
     * @param string
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String encodeByMD5(String string){
        //确定计算方法
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            log.warn(ex.getMessage(), ex);
//            ex.printStackTrace();
        }
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encodeString = null;
        try {
            encodeString = base64Encoder.encode(md5.digest(string.getBytes("utf-8")));
        } catch (UnsupportedEncodingException ex) {
            log.warn(ex.getMessage(), ex);
//            ex.printStackTrace();
        }

        return encodeString;
    }
    //将userDTO转化为userPasswdDO
    public static UserPasswdDO convertUserPasswdDOFromDTO(UserDTO userDTO){
        if(userDTO == null)
            return null;
        UserPasswdDO userPasswdDO = new UserPasswdDO();
        BeanUtils.copyProperties(userDTO,userPasswdDO);

        return userPasswdDO;
    }
    //将userDTO转化为userDO
    public static UserDO convertUserDOFromDTO(UserDTO userDTO){
        if(userDTO == null)
            return null;
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userDTO,userDO);

        return userDO;
    }
    //将userDO和userPassDO合并转化为DTO
    public static UserDTO convertUserDTOFromDO(UserDO userDO, UserPasswdDO userPasswdDO){
        if(userDO == null)
            return null;
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO,userDTO);
        if (userPasswdDO != null) {
            userDTO.setEncrptPasswd(userPasswdDO.getEncrptPasswd());
        }

        return userDTO;
    }
}
