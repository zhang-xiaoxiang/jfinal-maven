package com.demo.jfinal.controller;

import com.demo.jfinal.model.User;
import com.jfinal.core.Controller;
import com.jfinal.core.NotAction;
import com.jfinal.kit.PropKit;

import java.util.HashMap;
import java.util.Map;

/**
 * IndexController:控制层(这个是用来测试的)
 *
 * @author zhangxiaoxiang
 * @date 2019/9/19
 */
public class IndexController extends Controller {
    /**
     * 默认映射路径(包含错误路径)
     */
    public void index() {
        renderText("这是直接访问到接口控制层的默认方法,错误的路径也被自动映射到这里来......");
    }

    /**
     * 测试读取配置文件 这里写在代码耦合度高,这里测试而已
     */
    public void readconfig() {
        //获取键值对的值,注意乱码问题
        String proStr = PropKit.use("application.properties").get("userName");
        String proStr2 = PropKit.use("application.properties").get("email");
        renderText("测试渲染文本,并读取配置文件\n userName:" + proStr + "\temail:" + proStr2);
    }

    /**
     * 测试渲染文本
     */
    public void text() {
        renderText("我是渲染文本!");
    }

    /**
     * 测试渲染json  map,对象都可以哈
     */
    public void json() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "我是json");
        map.put("age", 18);
        renderJson(map);
        User user = new User();
        user.setUserId("12345");
        user.setUserName("张晓祥");
        renderJson(user);
    }

    /**
     * 测试渲染存HTML
     */
    public void html() {
        renderHtml("<h2 style='color: red'>测试渲染存HTML</h2");
    }

    /**
     * 测试渲染FreeMarker
     */
    public void ftl() {
        setAttr("name", "我是FreeMarker哈哈哈");
        renderFreeMarker("index.ftl");

    }


    /**
     * 不希望成为 action，仅供子类调用，或拦截器中调用
     */
    @NotAction
    public String getNotActionTest() {
        String s = "我是来自普通方法的一个参数";
        return s;
    }

    public void test() {
        String test = getNotActionTest();
        renderText(test);
    }

    /**
     * 带参的接口
     *
     * @param param
     */
    public void testParam(String param, Integer age) {
        String param1 = getPara("param");
        Integer age1 = getInt("age");
        renderText("请求参数是姓名:" + param1 + "  年龄  " + age1);
    }
}
