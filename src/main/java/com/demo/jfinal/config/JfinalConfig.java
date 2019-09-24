package com.demo.jfinal.config;

import com.demo.jfinal.controller.IndexController;
import com.demo.jfinal.controller.ProjectController;
import com.demo.jfinal.controller.UserController;
import com.demo.jfinal.exception.ControllerException;
import com.demo.jfinal.exception.ServiceException;
import com.demo.jfinal.model._MappingKit;
import com.jfinal.config.*;
import com.jfinal.json.FastJsonFactory;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

/**
 * AppConfig:jfinal的各种配置类
 *
 * @author zhangxiaoxiang
 * @date 2019/9/20
 */
public class JfinalConfig extends JFinalConfig {
    /**
     * 此方法用来配置访问路由，如下代码配置了将 "/hello" 映射到HelloController这个控制器，
     * 通过以下的配置，http://localhost/hello  将访问 HelloController.index() 方法，
     * 而http://localhost/hello/methodName  将访问到 HelloController.methodName() 方法
     *
     * @param me
     */
    @Override
    public void configRoute(Routes me) {
        //配置一个模块对应的controller,里面可以包含很多接口(就是action哈,老夫叫不惯)
        me.setBaseViewPath("src/webapp/WEB-INF/view");
        // 第三个参数省略时， basePath 取第一个参数的值 : "/project"
        me.add("/project", ProjectController.class);
        me.add("/index", IndexController.class);
        me.add("/user", UserController.class);

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
        // 开启对 jfinal web 项目组件 Controller、Interceptor、Validator 的注入,类似spring的自动注入
        me.setInjectDependency(true);
        // 开启对超类的注入。不开启时可以在超类中通过 Aop.get(...) 进行注入
        me.setInjectSuperClass(true);
        // jfinal 官方提供了 Json  抽象类的三个实现：JFinalJson、FastJson、Jackson，
        // 如果不进行配置，那么默认使用 JFinalJson 实现(注意添加相关的依赖)
        // me.setJsonFactory(new FastJsonFactory());
        //    设置模板引擎(枚举类型)
        // me.setViewType(ViewType.FREE_MARKER);



    }


    /**
     * 此方法用来配置Template Engine 模板引擎
     *
     * @param me
     */
    @Override
    public void configEngine(Engine me) {
        // 支持模板热加载，绝大多数生产环境下也建议配置成 true，除非是极端高性能的场景
        me.setDevMode(true);

        // 添加共享模板函数
        // me.setBaseTemplatePath("src/webapp/WEB-INF/view");
        // me.addSharedFunction("/index.html");

        // 配置极速模式，性能提升 13%
        Engine.setFastMode(true);

    }

    /**
     * 此方法用来配置JFinal的各种插件Plugin,比如数据库,模板引擎
     *
     * @param me
     */
    @Override
    public void configPlugin(Plugins me) {
        //-------------------------------------------配置数据库-------------------------------------------
        //配置数据源
        DruidPlugin dp = new DruidPlugin(PropKit.use("application.properties").get("jdbcUrl"),
                PropKit.use("application.properties").get("user"),
                PropKit.use("application.properties").get("password").trim());
        me.add(dp);
        //jfinal数据库插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        me.add(arp);
        //开启调试模式的SQL显示
        arp.setShowSql(true);
        arp.setDevMode(true);
        //配置SQL 模板与查询
        arp.addSqlTemplate("all.sql");
        //*****************************************************************************************
        //_MappingKit.mapping(arp); 这是生成后添加的_MappingKit这是官方起的名字,这里不配置操作数据库就会出现空指针
        //*****************************************************************************************
        _MappingKit.mapping(arp);

        //-------------------------------------------配置模板引擎-------------------------------------------
        Engine engine = arp.getEngine();
        // 上面的代码获取到了用于 sql 管理功能的 Engine 对象，接着就可以开始配置了
        engine.setToClassPathSourceFactory();
        engine.addSharedMethod(new StrKit());
        me.add(arp);


    }

    /**
     * 逆向生成数据库需要的插件(自己写的,方便main方法调用)
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
        // 添加控制层全局拦截器
        me.addGlobalActionInterceptor(new ControllerException());
        // 添加业务层全局拦截器
        // me.addGlobalServiceInterceptor(new ServiceException());
        //    替他拦截器(登录验证,日志等...)
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