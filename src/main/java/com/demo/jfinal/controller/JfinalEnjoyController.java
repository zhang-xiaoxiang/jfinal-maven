package com.demo.jfinal.controller;

import com.demo.jfinal.interceptor.AllInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import java.util.*;

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
        String name = get("name","张三");
        set("name", name);
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(list.size());
        set("stringList",list);
        Map<String,Object> map=new HashMap<>();
        map.put("mapkey","mapvalue");
        map.put("mapkey2",true);
        set("map",map);
        render("index.html");
    }
}
