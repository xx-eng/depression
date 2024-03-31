package com.syzc.controller;

import com.baidu.aip.nlp.AipNlp;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.kit.HashKit;
import com.syzc.Kit.BaseResponse;
import com.syzc.model.User;
import com.syzc.service.UserService;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 12:12
 * @description
 */
public class UserController extends Controller {
    BaseResponse baseResponse = new BaseResponse();
    UserService userService = new UserService();
    public void index() {
        String APP_ID = "25686837";
        String API_KEY = "l4WkRnFqSZjFmIGxAMKu9SwF";
        String  SECRET_KEY = "sVzezpqTpMRx836oYt5ood0q8uNv0vh9";

        AipNlp client = new AipNlp(APP_ID, API_KEY,SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        String text = "百度是一家高科技公司";
        HashMap<String, Object> options = new HashMap<String, Object>();

        // 情感倾向分析
        JSONObject res = client.sentimentClassify(text, options);
        System.out.println(res.toString(2));
        System.out.println(res.getJSONArray("items").getJSONObject(0).get("sentiment").toString());

    }

    /**
     * 登录
     */
    public void login() {
        String mobile = getPara("mobile");
        String password = getPara("password");
        baseResponse = userService.login(mobile, password);
        renderJson(baseResponse);
    }

    /**
     * 获取所有用户
     */
    public void getAllUsers() {
        baseResponse = userService.getAllUsers();
        System.out.println(baseResponse.getData());
        renderJson(baseResponse);
    }

    /**
     * 获取一个用户的信息
     */
    public void getUser(){
        String id_s = getPara("id");
        long id = Long.parseLong(id_s);
        System.out.println("id:" + id);
        baseResponse = userService.getUser(id);
        renderJson(baseResponse);
    }

    /**
     * 删除用户
     */
    public void deleteUser(){
        String id_s =  getPara("id");
        long id = Long.parseLong(id_s);
        System.out.println(id);
        baseResponse = userService.deleteUser(id);
        renderJson(baseResponse);
    }

    /**
     * @param user
     * 更新用户信息
     */
    public void updateUser(@Para("") User user){
        System.out.println(user);
        String salt = user.getSalt();
        String password = HashKit.sha256(salt + user.getPassword());
        user.setPassword(password);
        baseResponse = userService.updateUser(user);
        renderJson(baseResponse);
    }


    /**
     * 增加用户，注册
     */
    public void addUser(){
        //用户头像之后写！！！
        Long photoId = Long.parseLong(getPara("photoId"));
        String url = getPara("url");
        String account = getPara("account");
        String mobile = getPara("mobile");
        String email = getPara("email");
        String password_i = getPara("password");
        String salt = HashKit.generateSaltForSha256();
        int status = Integer.parseInt(getPara("status"));
        String password = HashKit.sha256(salt+ password_i);
        baseResponse = userService.addUser(photoId, url, account, mobile, email, password, salt, status);
        renderJson(baseResponse);

    }
}
