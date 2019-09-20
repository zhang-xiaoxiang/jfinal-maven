package com.demo.jfinal.service;

import com.demo.jfinal.model.Address;
import com.demo.jfinal.model.User;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * UserService:用户服务层
 *
 * @author zhangxiaoxiang
 * @date 2019/9/20
 */
public class UserService {
    /**
     * 这里相当于注入dao
     */
    public static final User DAO = new User().dao();

    public List<User> user() {
        List<User> all = DAO.findAll();
        return all;
    }

    //   Db + Record模式-----------------------------------直接在controller层使用貌似也行

    /**
     * 添加用户
     *
     * @return
     */
    public boolean adduser() {
        //注意主键
        Record user = new Record().set("user_id","123").set("user_name", "我是jfinal添加的用户").set("user_phone", "13512345678");
        return Db.save("user", user);
    }
    public boolean deluser() {
        //注意主键(不是默认的id就要用重载的方法)
        boolean b = Db.deleteById("user", "user_id","123");
       return b;
    }

    public List<User> testpage(){
        Page<User> paginate = DAO.paginate(2, 2, "select *", "from user ");
        List<User> list = paginate.getList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        return list;
    }

    /**
     * 连表查询地址表的用户姓名
     * @return
     */
    public String relation() {
        String sql = "select ad.*, us.user_name from address ad inner join user us on ad.user_id=us.user_id where ad.address_id=?";
        Address address=AddressService.DAO.findFirst(sql,"1560839798862486320");
        String name = address.getAddressName();
        System.out.println(name);
        return name;
    }





}
