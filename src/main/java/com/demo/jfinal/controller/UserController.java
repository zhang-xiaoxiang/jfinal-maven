package com.demo.jfinal.controller;

import com.demo.jfinal.exception.ControllerException;
import com.demo.jfinal.exception.GlobalExceptions;
import com.demo.jfinal.interceptor.DemoInterceptor;
import com.demo.jfinal.model.User;
import com.demo.jfinal.result.ResultData;
import com.demo.jfinal.service.UserService;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * UserController:用户控制层
 *
 * @author zhangxiaoxiang
 * @date 2019/9/20
 */
@Before(ControllerException.class)
public class UserController extends Controller {
    /**
     * 自动注入,类比spring 自动注入
     */
    @Inject
    UserService userService;


    public void user() {
        List<User> user = userService.user();
        renderText("全查询用户:  " + user);
    }

    public void saveuser() {
        boolean adduser = userService.saveuser();
        if (adduser) {
            renderJson(ResultData.success("添加成功!"));
        } else {
            renderJson(ResultData.success("添加失败!"));
        }
    }

    public void deluser() {
        String userId = get("userId");
        boolean adduser = userService.deluser(userId);
        if (adduser) {
            renderText("删除成功!");
        } else {
            renderText("删除失败!");
        }
    }

    //---------------------    直接在controller层使用Db + Record模式貌似也行    -------------------------------------



    public void getuser() {
        Record user = userService.findUser(get("userId"));
        renderJson(ResultData.success("查询成功!", user));


    }


    public void test1() {
        List<Object[]> list = Db.query("select user_id, user_name, user_password from user");
        System.out.println(list);
        list.forEach(objects -> System.out.println(list.toString()));
        renderText("测试查询多个字段");
    }

    public void test2() {
        List<User> testpage = userService.testpage();
        // renderText("测试分页");

        renderJson("data", testpage);
    }

    /**
     * 测试连表查询
     */
    public void relation() {
        String relation = userService.relation();
        renderText(relation);
    }

    /**
     * 测试模板引擎
     */
    public void test3() {
        List<User> testpage = userService.testpage();
        setAttr("key", "张晓祥测试指令哈");
        setAttr("testpage", testpage);
        render("share.html");
    }


}
