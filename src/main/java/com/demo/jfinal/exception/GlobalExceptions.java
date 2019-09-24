package com.demo.jfinal.exception;


import com.jfinal.config.*;
import lombok.extern.slf4j.Slf4j;


/**
 * GlobalExceptions:全局异常拦截(jfinal是用拦截器AOP做的异常拦截机制)
 * @author zhangxiaoxiang
 * @date 2019/9/24
 */
 // @Slf4j
public abstract class GlobalExceptions  extends JFinalConfig {
    /**
     * 这里是配置异常拦截器
     * @param me
     */
    @Override
    public void configInterceptor(Interceptors me) {
        // 添加控制层全局拦截器
        me.addGlobalActionInterceptor(new ControllerException());

        // 添加业务层全局拦截器
        me.addGlobalServiceInterceptor(new ServiceException());
    }
}
