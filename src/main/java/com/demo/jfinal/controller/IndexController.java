package com.demo.jfinal.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.NotAction;
import com.jfinal.core.paragetter.Para;
import com.jfinal.kit.PropKit;

import javax.servlet.http.HttpServletRequest;

/**
 * HelloController:控制层
 * <p>
 * 一个Controller可以包含多个Action。Controller是线程安全的。
 *
 * @author zhangxiaoxiang
 * @date 2019/9/19
 */
public class IndexController extends Controller {
    /**
     * http://localhost/hello/xxx方法,如果xxx方法匹配到了就进入相应方法(方法称为Action)
     * 没有这个就是默认的index(),如果改成index1()那么这个就达不到默认的方法的效果
     */
    // public void index() {
    //     //     renderText("这是直接访问到接口控制层的默认方法!");
    //     // }

    /**
     * 在 Controller 之中定义的 public 方法称为Action,和spring boot里面的接口方法是一个意思
     * 这里和配置类的最好一直,免得出现命名荒,哈哈
     */
    public void hello() {
        //获取键值对的值,注意乱码问题
        String proStr = PropKit.use("application.properties").get("userName");
        String proStr2 = PropKit.use("application.properties").get("email");
        renderText("Hello JFinal World,我是 " + proStr + "  电子邮件" + proStr2);
    }

    public void hello2() {
        renderText("Hello JFinal World2!");
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
     * @param param
     */
    public void testParam(String param,Integer age) {
        String param1 = getPara("param");
        Integer age1 = getInt("age");
        renderText("请求参数是姓名:" +param1+"  年龄  "+age1);
    }
}
