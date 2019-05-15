package com.demo.seckill.controller;

import com.demo.seckill.enums.BusinessErrorEnum;
import com.demo.seckill.exception.BusinessException;
import com.demo.seckill.response.CommonReturnType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by liyun on 2019/3/25
 *
 * 使用 @ControllerAdvice + @ExceptionHandler 进行全局的 Controller 层异常处理
 */
//@ControllerAdvice 注解定义全局异常处理类
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{

    //处理BusinessException
    //@ExceptionHandler 注解声明异常处理方法
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public Object businessExceptionHandler(HttpServletRequest request, BusinessException ex){
        log.warn(ex.getErrorMsg(), ex);
        Map<String, Object> data = new HashMap<>();
        data.put("errorCode", ex.getErrorCode());
        data.put("errorMsg", ex.getErrorMsg());

        return new CommonReturnType(Boolean.FALSE,data);
    }
//    //处理ConstraintViolationException  单个参数异常
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Object ConstraintViolationExceptionHandler(HttpServletRequest request,ConstraintViolationException ex) {
//        log.warn(ex.getMessage(), ex);
//        List<String> errorMsg = ex.getConstraintViolations().stream().map(v -> v.getMessage()).collect(Collectors.toList());
//        Map<String, Object> data = new HashMap<>();
//        data.put("errorCode",BusinessErrorEnum.PARAMETER_VALIDATION_ERROR.getErrorCode());
//        data.put("errorMsg",errorMsg);
//        return new CommonReturnType(Boolean.FALSE,data);
//    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object ValidationExceptionHandler(ValidationException ex) {
        log.warn(ex.getMessage(), ex);
        String errorMsg = ex.getMessage();
        Map<String, Object> data = new HashMap<>();
        data.put("errorCode",BusinessErrorEnum.PARAMETER_VALIDATION_ERROR.getErrorCode());
        data.put("errorMsg",errorMsg);
        return new CommonReturnType(Boolean.FALSE,data);
    }
    /**
     * 处理实体字段校验不通过异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        log.warn(ex.getMessage(), ex);
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMsg = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        Map<String, Object> data = new HashMap<>(4);
        data.put("errorCode",BusinessErrorEnum.PARAMETER_VALIDATION_ERROR.getErrorCode());
        data.put("errorMessages",errorMsg);
        return new CommonReturnType(Boolean.FALSE,data);
    }
//    //处理BindException  对象参数异常
//    @ExceptionHandler(BindException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Object BindExceptionHandler(BindException ex) {
//        log.warn(ex.getMessage(), ex);
//        BindingResult bindingResult = ex.getBindingResult();
//        List<String> errorMsg = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
//        Map<String, Object> data = new HashMap<>(4);
//        data.put("errorCode",BusinessErrorEnum.PARAMETER_VALIDATION_ERROR.getErrorCode());
//        data.put("errorMessages",errorMsg);
//        return new CommonReturnType(Boolean.FALSE,data);
//    }
    //其他未处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object exceptionHandler(HttpServletRequest request, Exception ex){
        log.warn(ex.getMessage(), ex);
        Map<String, Object> data = new HashMap<>();
        data.put("errorCode", BusinessErrorEnum.UNKNOWN_ERROR.getErrorCode());
        data.put("errorMsg", BusinessErrorEnum.UNKNOWN_ERROR.getErrorMsg());

        return new CommonReturnType(Boolean.FALSE,data);
    }
}

