package com.demo.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import javax.servlet.http.HttpServletResponse;

/**
 * CORSInterceptor:解决前后端分离的跨域问题
 *
 * @author zhangxiaoxiang
 * @date 2019/9/25
 */
public class CORSInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        inv.invoke();
        System.out.println("------------------CORSInterceptor-------------------");
        HttpServletResponse response = inv.getController().getResponse();
        response.addHeader("Access-Control-Allow-Origin", "*");
    }
}