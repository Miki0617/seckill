package com.demo.seckill.exception;

/**
 * Created by liyun on 2019/3/25
 */
public interface CommonError {

   public int getErrorCode();

   public String getErrorMsg();

   public CommonError setErrorMsg(String errorMsg);
}
