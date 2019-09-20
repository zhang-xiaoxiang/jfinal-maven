package com.demo.jfinal.service;

import com.demo.jfinal.model.User;

public class UserService {
    public static final User dao = new User().dao();
    public User getById(){
        User byId = dao.findById(11);
        return byId;
    }
}
