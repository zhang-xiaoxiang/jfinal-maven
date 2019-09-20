package com.demo.jfinal.controller;

import com.demo.jfinal.model.User;
import com.demo.jfinal.service.UserService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

public class UserController extends Controller {
    @Inject
    UserService userService;

    public void getUser(){
        User byId = userService.getById();
        renderText("测试 "+byId);
    }



}
