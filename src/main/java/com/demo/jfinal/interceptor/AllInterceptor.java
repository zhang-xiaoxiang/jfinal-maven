package com.demo.jfinal.interceptor;

import com.jfinal.aop.InterceptorStack;
/**
 * AllInterceptor:统一管理所有的拦截器(注意顺序)
 *
 * @author zhangxiaoxiang
 * @date 2019/9/25
 */
public class AllInterceptor extends InterceptorStack {
    @Override
    public void config() {
        CORSInterceptor corsInterceptor=new CORSInterceptor();
        ControllerException controllerException=new ControllerException();
        addInterceptors(corsInterceptor,controllerException);
    }
}
