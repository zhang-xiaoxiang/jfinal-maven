package com.demo.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;


/**
 * AuthInterceptor:Interceptor 可以对方法进行拦截，并提供机会在方法的前后添加切面代码，实现 AOP 的核心目标
 * @author XXX
 * @date 2019/9/19
 */


public class AuthInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        System.out.println("方法(接口)调用的前置处理 Before method invoking");
        invocation.invoke();
        System.out.println("方法(接口)调用的后置置处理 After method invoking");
    }
}
