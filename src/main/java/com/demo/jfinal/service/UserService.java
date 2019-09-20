package com.demo.jfinal.service;

import com.demo.jfinal.model.User;

import java.util.List;
/**
 * UserService:用户服务层
 * @author zhangxiaoxiang
 * @date 2019/9/20
 */
public class UserService {
    /**
     * 这里相当于注入dao
     */
    public static final User DAO = new User().dao();
    public  List<User> user(){
        List<User> all = DAO.findAll();
        return all;
    }


}
