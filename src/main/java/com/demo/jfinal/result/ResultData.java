package com.demo.jfinal.result;

/**
 * ResultData:封装数据且以json格式返回
 *
 * @author zhangxiaoxiang
 * @date: 2019/06/25
 */
public class ResultData {


    /**
     * 成功返回
     *
     * @param msg 成功返回信息
     * @return
     */
    public static Result success(String msg) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     *
     * @param msg 成功返回信息
     * @param data 返回数据
     * @return
     */
    public static Result success(String msg, Object data) {
        Result result = new Result();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 失败返回
     *
     * @param msg 错误信息
     * @return
     */
    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 失败返回
     * @param code 带状态码
     * @param msg  错误信息
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 身份验证失败返回
     *
     * @param msg 失败信息
     * @return
     */
    public static Result noVerify(String msg) {
        Result result = new Result();
        result.setCode(401);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
