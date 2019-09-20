package com.demo.jfinal.controller;

import com.demo.jfinal.model.User;
import com.demo.jfinal.service.UserService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

import java.util.List;

/**
 * UserController:用户控制层
 *
 * @author zhangxiaoxiang
 * @date 2019/9/20
 */
public class UserController extends Controller {
    /**
     * 自动注入,类比spring
     */
    @Inject
    UserService userService;

    public void user() {
        List<User> user = userService.user();
        renderText("全查询用户:  " + user);
    }

    public void adduser() {
        boolean adduser = userService.adduser();
        if (adduser) {
            renderText("添加成功!");
        } else {
            renderText("添加失败!");
        }
    }

    public void deluser() {
        boolean adduser = userService.deluser();
        if (adduser) {
            renderText("删除成功!");
        } else {
            renderText("删除失败!");
        }
    }
    //    直接在controller层使用Db + Record模式貌似也行

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
