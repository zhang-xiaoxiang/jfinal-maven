package com.demo.jfinal.service;

import com.demo.jfinal.model.User;

import java.util.List;

public class UserService {
    public static final User dao = new User().dao();
    public  List<User> user(){
        List<User> all = dao.findAll();
        return all;
    }
}
