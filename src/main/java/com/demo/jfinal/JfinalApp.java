package com.demo.jfinal;

import com.demo.jfinal.config.JfinalConfig;
import com.jfinal.server.undertow.UndertowServer;

import javax.servlet.DispatcherType;

/**
 * JfinalApp:类似springboot的启动类
 *
 * @author zhangxiaoxiang
 * @date 2019/9/20
 */
public class JfinalApp {
    public static void main(String[] args) {

        UndertowServer.start(JfinalConfig.class, 80, true);
        System.out.println("页面       http://localhost/project/project");
        System.out.println("返回字符串  http://localhost/index/");
        System.out.println("查询数据库  http://localhost/user/user");
    }
}
