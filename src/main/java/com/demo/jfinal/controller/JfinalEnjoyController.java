package com.demo.jfinal.controller;

import com.demo.jfinal.interceptor.AllInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
/**
 * JfinalEnjoyController:测试jfinal模板引擎的
 * @author zhangxiaoxiang
 * @date 2019/9/25
 */
@Before(AllInterceptor.class)
public class JfinalEnjoyController extends Controller {
    /**
     * 测试渲染推荐的enjoy引擎
     */
    public void enjoy() {
        String name = get("name","我是默认的值哈哈,你说气不气");
        set("name", name);
        render("index.html");
    }
}
