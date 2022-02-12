package com.syzc.config;

import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
//import com.syzc.controller.AdminController;
import com.syzc.controller.*;
import com.syzc.model.Record;
import com.syzc.model._MappingKit;
//import com.syzc.model._MappingKit;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-16 11:29
 * @description
 */
public class MyConfig extends JFinalConfig {
    static Prop p;
    static void loadConfig() {
        if(p == null){
            p =  PropKit.use("config.properties");
        }
    }
    public static DruidPlugin createDruidPlugin() {
        loadConfig();
        return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
    }
    @Override
    public void configConstant(Constants constants) {
        loadConfig();
        constants.setDevMode(p.getBoolean("devMode", false));
        //设置视图类型为JSP，否则默认为FreeMarker
        constants.setViewType(ViewType.JSP);
    }

    @Override
    public void configRoute(Routes routes) {
        //配置路由，默认访问index,更改
        routes.add("/user", UserController.class);
        routes.add("/admin", AdminController.class);
        routes.add("/article", ArticleController.class);
        routes.add("/comment", CommentController.class);
        routes.add("/posts", PostsController.class);
        routes.add("/question", QuestionController.class);
        routes.add("/answer", AnswerController.class);
        routes.add("/record", RecordController.class);
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        loadConfig();
//        DruidPlugin druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"),p.get("password"),"com.mysql.cj.jdbc.Driver");
        DruidPlugin druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
        plugins.add(druidPlugin);
        //配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        //显示sql语句
        arp.setShowSql(PropKit.getBoolean("devMode"));
        // 所有映射在 MappingKit 中自动化搞定
        _MappingKit.mapping(arp);
        plugins.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
