package com.demo.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
/**
 * ServiceException:这里配置业务层异常拦截器
 * @author zhangxiaoxiang
 * @date 2019/9/24
 */
public class ServiceException implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        // System.out.println("业务层异常拦截器和控制层异常拦截类似,这里就不演示了");
    }
}
