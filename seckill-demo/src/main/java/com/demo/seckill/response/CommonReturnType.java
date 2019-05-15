package com.demo.seckill.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by liyun on 2019/3/24
 * 公共返回类型
 */
@Data
@NoArgsConstructor
public class CommonReturnType {
    /**
     * 是否成功
     * 如果success = TRUE，则返回前端需要的数据
     * 如果success = FALSE，则返回通用的错误码格式
     */
    private Boolean success;
    /**
     * 数据
     */
    private Object data;
    /**
     * 正确数据的构造函数
     *
     * @param data 数据
     */
    public CommonReturnType(Object data) {
        this.success = Boolean.TRUE;
        this.data = data;
    }

    public CommonReturnType(Boolean success, Object data) {
        this.success = success;
        this.data = data;
    }
}
