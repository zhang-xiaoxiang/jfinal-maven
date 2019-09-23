package com.demo.jfinal.result;

import lombok.Data;


/**
 * ResultVO:http 接口返回给前端的数据,给前段Ajax进行解析
 *
 * @author zhangxiaoxiang
 * @date: 2019/05/23
 */
@Data
public class Result<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;

    /**
     * 前端接收到的数据
     * 空值不反回那么用下面的注解
     *
     * @JsonInclude(JsonInclude.Include.NON_NULL)
     */
    private T data;


}
