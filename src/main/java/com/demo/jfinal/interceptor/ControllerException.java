package com.demo.jfinal.interceptor;

import com.demo.jfinal.result.ResultData;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * ControllerException:控制器异常拦截(在这里顺带把跨域解决了,注意顺序)
 * @author zhangxiaoxiang
 * @date 2019/9/24
 */
public class ControllerException implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        //获取到调用的控制器
        Controller controller = inv.getController();
        try {

            inv.invoke();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常: " + e.getMessage());
            controller.renderJson(ResultData.error("系统异常!"));
            System.out.println("控制层异常日志===> "+e.getMessage());

        }finally {
            //这里记录日志也可以
        }
        System.out.println("控制层异常拦截--------AOP------后");
    }
}
