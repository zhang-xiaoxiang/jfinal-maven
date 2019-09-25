package com.demo.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;


public class DemoInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
       System.out.println("Before method invoking");
        Controller controller = inv.getController();
       inv.invoke();
       System.out.println("After method invoking");
    }
}