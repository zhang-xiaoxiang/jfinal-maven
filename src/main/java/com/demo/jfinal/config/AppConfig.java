package com.demo.jfinal.config;

import com.demo.jfinal.controller.IndexController;
import com.demo.jfinal.controller.ProjectController;
import com.demo.jfinal.controller.UserController;
import com.demo.jfinal.model._MappingKit;
import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 * AppConfig:jfinal的各种配置类
 *
 * @author zhangxiaoxiang
 * @date 2019/9/20
 */
public class AppConfig extends JFinalConfig {
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
        // 开启对 jfinal web 项目组件 Controller、Interceptor、Validator 的注入,类似spring的自动注入
        me.setInjectDependency(true);
        // 开启对超类的注入。不开启时可以在超类中通过 Aop.get(...) 进行注入
        me.setInjectSuperClass(true);
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
        //网络上的(暂时以没有成功)
        me.add("/uploadFile", ProjectController.class);
        me.add("/hello", IndexController.class);
        me.add("/hello2", IndexController.class);
        me.add("/test", IndexController.class);
        me.add("/testParam", IndexController.class);
        me.add("/user", UserController.class);
    }

    /**
     * 此方法用来配置Template Engine
     *
     * @param me
     */
    @Override
    public void configEngine(Engine me) {
        // me.addSharedFunction("/view/common/layout.html");
        // me.addSharedFunction("/view/common/paginate.html");
        // me.addSharedFunction("/view/admin/common/layout.html");
    }

    /**
     * 此方法用来配置JFinal的Plugin，操作数据库的
     * 如下代码配置了Druid数据库连接池插件与ActiveRecord数据库访问插件。
     * 通过以下的配置，可以在应用中使用ActiveRecord非常方便地操作数据库
     *
     * @param me
     */
    @Override
    public void configPlugin(Plugins me) {
        DruidPlugin dp = new DruidPlugin(PropKit.use("application.properties").get("jdbcUrl"),
                PropKit.use("application.properties").get("user"),
                PropKit.use("application.properties").get("password").trim());
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        me.add(arp);
        //下面是手动配置的(不推荐)
        // arp.addMapping("user", "user_id",User.class);
        //这是生成后添加的_MappingKit这是官方起的名字(建议不用改了),这里不配置操作数据库就会出现空指针
        _MappingKit.mapping(arp);
    }

    /**
     * 逆向生成数据库需要的插件
     *
     * @return
     */
    public static DruidPlugin createDruidPlugin() {
        //读取数据源信息
        DruidPlugin dp = new DruidPlugin(PropKit.use("application.properties").get("jdbcUrl"),
                PropKit.use("application.properties").get("user"),
                PropKit.use("application.properties").get("password").trim());
        return dp;
    }

    /**
     * 此方法用来配置JFinal的全局拦截器，全局拦截器将拦截所有 action 请求，
     * 除非使用@Clear在Controller中清除，如下代码配置了名为AuthInterceptor的拦截器
     *
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
     *
     * @param me
     */
    @Override
    public void configHandler(Handlers me) {
        //注意：Handler 是全局共享的，所以要注意其中声明的属性的线程安全问题
        // me.add(new ResourceHandler());
    }

    /**
     * 系统启动完成后回调
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