package com.demo.seckill.enums;

import com.demo.seckill.exception.CommonError;

/**
 * Created by liyun on 2019/3/25
 */
public enum BusinessErrorEnum implements CommonError {
    //通用错误类型 10000
    UNKNOWN_ERROR(10001,"业务异常错误"),
    PARAMETER_VALIDATION_ERROR(10002,"参数不合法"),
    PARAMETER_VALIDATION_EMPTY(10003,"参数为空"),
    //用户信息相关错误类型 20000
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_IS_EXIST(20002,"用户已存在"),
    USER_LOGIN_FAIL(20003,"用户名或者密码错误"),
    USER_NOT_LOGIN(20004,"用户未登录"),
    //交易信息相关错误类型 30000
    STOCK_NOT_ENOUGH(30001,"库存不足")
    ;

    private int errorCode;
    private String errorMsg;

    private BusinessErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
