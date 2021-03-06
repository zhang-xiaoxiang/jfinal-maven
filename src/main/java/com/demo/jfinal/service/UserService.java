package com.demo.jfinal.service;

import com.demo.jfinal.model.Address;
import com.demo.jfinal.model.User;
import com.demo.jfinal.result.PageResponse;
import com.jfinal.aop.Inject;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import javafx.scene.control.Pagination;

import java.util.List;

/**
 * UserService:用户服务层
 *
 * @author zhangxiaoxiang
 * @date 2019/9/20
 */
public class UserService {
    @Inject
  private   PageResponse pageResponse;
    /**
     * 这里相当于注入dao
     */
    private static final User DAO = new User().dao();
    /**
     * 用于  Db + Record模式
     */
    private static final String TABLENAME = "user";
    private static final String PRIMARYKEY = "user_id";

    /**
     * 查询所有的用户(常规操作)
     *
     * @return
     */
    public List<User> user() {
        return DAO.findAll();
    }

    //   Db + Record模式------------------------直接在controller层使用貌似也行,类似mybatis plus的额通用service--------------

    /**
     * 添加用户
     *
     * @return
     */
    public boolean saveuser() {
        //注意主键
        Record user = new Record().set("user_id", "124").set("user_name", "我是jfinal添加的用户").set("user_phone", "13512345678");
        return Db.save("user", user);
    }

    /**
     * 删除用户
     *
     * @return
     */
    public boolean deluser(String userId) {
        //注意主键(不是默认的id就要用重载的方法)
        return Db.deleteById(TABLENAME, PRIMARYKEY, userId);
    }

    /**
     * 修改用户(加入事物)
     *
     * @return
     */
    public boolean upduser() {
        User user = new User();
        user.setUserId("124");
        Db.tx(() -> {
            Record record = Db.findById(TABLENAME, PRIMARYKEY, user.getUserId()).set("user_name", "我是修改后的2");
            return Db.update(TABLENAME, PRIMARYKEY, record);
        });
        return false;
    }


    /**
     * 根据ID查询用户
     *
     * @param userId
     * @return
     */
    public Record findUser(String userId) {
        return Db.findById(TABLENAME, PRIMARYKEY, userId);
    }

    /**
     * 测试分页(不带条件的)
     * @return
     */
    public PageResponse testpage(int pageNumber, int pageSize) {
        Page<User> paginate = DAO.paginate(pageNumber, pageSize, "select *", "from user ");
        pageResponse.setPageDataList(paginate);
        List<User> list = paginate.getList();
        return pageResponse;
    }

    /**
     * 连表查询地址表的用户姓名
     *
     * @return
     */
    public String relation() {
        String sql = "select ad.*, us.user_name from address ad inner join user us on ad.user_id=us.user_id where ad.address_id=?";
        Address address = AddressService.DAO.findFirst(sql, "1560839798862486320");
        String name = address.getAddressName();
        System.out.println(name);
        return name;
    }


}
