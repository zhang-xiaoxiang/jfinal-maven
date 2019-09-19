package com.demo.jfinal.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.render.JsonRender;
import com.jfinal.upload.UploadFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ProjectController:
 *
 * @author XXX
 * @date 2019/9/19
 */
public class ProjectController extends Controller {
    /**
     * 当需要打破 baseViewPath 与 viewPath 这两个参数的限制时，view 参数以 "/" 打头即可
     */
    public void project() {
        System.out.println("测试进入页面");
        render("abc.html");
    }

    /**
     * 测试文件下载
     */
    public void testGetFile() {
        String file = "src/webapp/download/测试文件.zip";
        renderFile(new File(file));
    }

    /**
     * 测试文件上传 https://yq.aliyun.com/articles/662154
     */
    public void testUploadFile() {
        File file = getFile().getFile();
        System.out.println(file.getName());
    }



    /**
     * 测试二维码生成
     * 'H'、'Q'、'M'、'L'，其纠错率分别为：30%、25%、15%、7%。 不指定该参数值默认为 'L'。
     */
    public void testQrCode() {
        // 二维码携带的数据
        String data = "weixin://wxpay/bizpayurl?appid=123456&mch_id=78910j";
        String data2 = "我可以带参数";
        // 渲染二维码图片，长度与宽度为 200 像素
        renderQrCode(data2, 200, 200);
    }

    /**
     * 为了便于项目支持集群与分布式，不建议使用 session 存放数据，
     * 建议将 session 范畴数据存放在数据库或者类似于 reids 的共享空间之中。
     *
     * ---未测试成功
     */
    // public void testSetSession(){
    //     setSessionAttr("sessiondemo", "我是测试");
    // }
    // public void testGetSession(){
    //     HttpSession session = getSession();
    //     System.out.println(session);
    // }


    




}
