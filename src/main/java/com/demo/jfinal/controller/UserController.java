package com.demo.jfinal.controller;

import com.demo.jfinal.exception.ControllerException;
import com.demo.jfinal.interceptor.DemoInterceptor;
import com.demo.jfinal.model.User;
import com.demo.jfinal.result.PageResponse;
import com.demo.jfinal.result.ResultData;
import com.demo.jfinal.service.UserService;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.Date;
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

    /**
     * 查询全部用户
     */
    public void user() {
        List<User> user = userService.user();
        renderText("全查询用户:  " + user);
    }

    /**
     * 增加用户
     */
    public void saveuser() {
        boolean adduser = userService.saveuser();
        if (adduser) {
            renderJson(ResultData.success("添加成功!"));
        } else {
            renderJson(ResultData.success("添加失败!"));
        }
    }

    /**
     * 根据ID删除用户
     */
    public void deluser() {
        String userId = get("userId");
        boolean adduser = userService.deluser(userId);
        if (adduser) {
            renderText("删除成功!");
        } else {
            renderText("删除失败!");
        }
    }

    /**
     * 更新用户
     */
    public void upduser() {
        try {
            userService.upduser();
            renderJson(ResultData.success("修改成功!"));
        } catch (Exception e) {
            e.fillInStackTrace();
            renderJson(ResultData.error("修改失败"));
        }

    }


    //---------------------    直接在controller层使用Db + Record模式貌似也行    -------------------------------------


    /**
     * 根据ID查询用户
     */
    public void getuser() {
        Record user = userService.findUser(get("userId"));
        renderJson(ResultData.success("查询成功!", user));
    }

    /**
     * 当 select 后的字段只有一个时，可以使用合适的泛型接收数据
     */
    public void queryone() {
        List<String> list = Db.query("select  user_name from user");
        System.out.println(list);
        list.forEach(objects -> System.out.println(list.toString()));
        renderJson(ResultData.success("查询字段成功", list));
    }

    /**
     * 当 select 后的字段多个时,必须使用 List<Object[]> 接收
     */
    public void querymany() {
        List<Object[]> list = Db.query("select user_id, user_name, user_password from user");
        System.out.println(list);
        list.forEach(objects -> System.out.println(list.toString()));
        renderJson(ResultData.success("查询字段成功", list));
    }

    /**
     * 测试分页,路径参数获取方式,url符合rest风格,但是不完全是,注意getParaToInt(),参考官方文档
     */
    public void testpage() {
        Integer pageNumber = getParaToInt(0,1);
        Integer pageSize = getParaToInt(1,5);
        PageResponse pageResponse = userService.testpage(pageNumber, pageSize);

        renderJson( ResultData.success("分页详情查询成功!",pageResponse));
    }

    /**
     * SQL 模板与查询
     */
    public void sqldemo() {
        String sql = Db.getSql("user.findGirl");
        List<Record> records = Db.find(sql, "0");
        renderJson(records);

    }
    /**
     * SQL 模板与查询2
     */
    public void sqldemo2() {
        // Db.template 用法（jfinal 4.0 新增）
        List<Record> records = Db.template("user.findcqboy", "重庆", 1).find();
        renderJson(records);

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
        PageResponse pageResponse = userService.testpage(1, 2);
        setAttr("key", "张晓祥测试指令哈");
        setAttr("testpage", pageResponse);
        render("share.html");
    }


}
