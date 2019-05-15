package com.demo.seckill.exception;

import lombok.NoArgsConstructor;

/**
 * Created by liyun on 2019/3/25
 */
@NoArgsConstructor
public class BusinessException extends RuntimeException implements CommonError{

    private CommonError commonError;

    //直接接收BusinessErrorEnum的传参用于构造业务异常
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }
    //接收自定义errorMsg的方式构造业务异常
    public BusinessException(CommonError commonError, String errorMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(errorMsg);
    }
    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.commonError.setErrorMsg(errorMsg);
        return this;
    }
}
