package com.demo.jfinal.controller;

import com.demo.jfinal.model.User;
import com.demo.jfinal.service.UserService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

import java.util.List;

public class UserController extends Controller {
    /**
     * 自动注入,类比spring
     */
    @Inject
    UserService userService;

    public void user(){
        List<User> user = userService.user();
        renderText("全查询用户:  "+user);
    }



}
