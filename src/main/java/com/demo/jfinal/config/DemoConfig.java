package com.demo.jfinal.config;

import com.demo.jfinal.controller.IndexController;
import com.demo.jfinal.controller.ProjectController;
import com.jfinal.config.*;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;

public class DemoConfig extends JFinalConfig {

    public static void main(String[] args) {
        UndertowServer.start(DemoConfig.class, 80, true);
    }

    /**
     * 此方法用来配置JFinal常量值
     *
     * @param me
     */
    @Override
    public void configConstant(Constants me) {
        //如开发模式常量devMode的配置，如下代码配置了JFinal运行在开发模式
        me.setDevMode(true);
        //文件下载与上传的配置基础路径
        me.setBaseDownloadPath("files");
        me.setBaseUploadPath("/upload");
    }

    /**
     * 此方法用来配置访问路由，如下代码配置了将 "/hello" 映射到HelloController这个控制器，
     * 通过以下的配置，http://localhost/hello  将访问 HelloController.index() 方法，
     * 而http://localhost/hello/methodName  将访问到 HelloController.methodName() 方法
     *
     * @param me
     */
    @Override
    public void configRoute(Routes me) {
        // 如果要将控制器超类中的 public 方法映射为 action 配置成 true，一般不用配置
        //  jfinal 3.6 新增了一个配置方法：setMappingSuperClass(boolean)，默认值为 false。
        //  配置成为 true 时，你的 controller 的超类中的 public 方法也将会映射成 action。
        //  如果你的项目中使用了 jfinal weixin 项目的 MsgController，则需要将其配置成 true，
        //  因为 MsgController 中的 index() 需要被映射成 action 才能正常分发微信服务端的消息。
        // me.setMappingSuperClass(false);
        // me.setBaseViewPath("/view");
        //前面是接口(spring叫法),后面是接口所在的控制层的类

        // baseViewPath 为 "src/webapp/WEB-INF/view"，该 Routes 对象之下映射的所有 Controller 都将取这个值(这是maven和动态web的不同之处)
        me.setBaseViewPath("src/webapp/WEB-INF/view");

        // basePath 为第三个参数 "/index"
        me.add("/", IndexController.class, "/index");

        // 第三个参数省略时， basePath 取第一个参数的值 : "/project"
        me.add("/project", ProjectController.class);
        me.add("/testGetFile", ProjectController.class);
        me.add("/testQrCode", ProjectController.class);
        me.add("/testSetSession", ProjectController.class);
        me.add("/testGetSession", ProjectController.class);
        me.add("/upload", ProjectController.class);
        me.add("/testUploadFile", ProjectController.class);
        me.add("/gotoUploadFile", ProjectController.class);
        //网络上的
        me.add("/uploadFile", ProjectController.class);






        me.add("/hello", IndexController.class);
        me.add("/hello2", IndexController.class);
        me.add("/test", IndexController.class);
        me.add("/testParam", IndexController.class);
    }

    /**
     * 此方法用来配置Template Engine
     * @param me
     */
    @Override
    public void configEngine(Engine me) {
        // me.addSharedFunction("/view/common/layout.html");
        // me.addSharedFunction("/view/common/paginate.html");
        // me.addSharedFunction("/view/admin/common/layout.html");
    }

    /**
     * 此方法用来配置JFinal的Plugin，
     * 如下代码配置了Druid数据库连接池插件与ActiveRecord数据库访问插件。
     * 通过以下的配置，可以在应用中使用ActiveRecord非常方便地操作数据库
     * @param me
     */
    @Override
    public void configPlugin(Plugins me) {

            // DruidPlugin dp = new DruidPlugin("jdbc:mysql://localhost/db_name", "userName", "password");
            // me.add(dp);
            // ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
            // me.add(arp);
            // arp.addMapping("user", User.class);
            // arp.addMapping("article", "article_id", Address.class);

    }

    /**
     * 此方法用来配置JFinal的全局拦截器，全局拦截器将拦截所有 action 请求，
     * 除非使用@Clear在Controller中清除，如下代码配置了名为AuthInterceptor的拦截器
     * @param me
     */
    @Override
    public void configInterceptor(Interceptors me) {
        //暂时不配置
        // me.add(new AuthInterceptor());
    }

    /**
     * 此方法用来配置JFinal的Handler，如下代码配置了名为ResourceHandler的处理器，
     * Handler可以接管所有web请求，并对应用拥有完全的控制权，可以很方便地实现更高层的功能性扩展
     * @param me
     */
    @Override
    public void configHandler(Handlers me) {
        //注意：Handler 是全局共享的，所以要注意其中声明的属性的线程安全问题
        // me.add(new ResourceHandler());
    }

    /**
     *  系统启动完成后回调
     */
    @Override
    public void onStart() {
        System.out.println("系统启动完成后回调 com.demo.jfinal.config.DemoConfig.onStart");
    }

    /**
     * 系统关闭之前回调
     */
    @Override
    public void onStop() {
        System.out.println("系统关闭之前回调 com.demo.jfinal.config.DemoConfig.onStop");
    }
}