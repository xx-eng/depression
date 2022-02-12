package com.syzc.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.kit.HashKit;
import com.syzc.Kit.BaseResponse;
import com.syzc.model.Admin;
import com.syzc.service.AdminService;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 15:34
 * @description
 */
public class AdminController  extends Controller {
    BaseResponse baseResponse = new BaseResponse();
    AdminService adminService = new AdminService();

    /**
     * 登录
     */
    public void login() {
        String mobile = getPara("mobile");
        String password = getPara("password");
        baseResponse = adminService.login(mobile, password);
        renderJson(baseResponse);
    }


    /**
     * 获取一个用户的信息
     */
    public void getAdmin(){
        String id_s = getPara("id");
        long id = Long.parseLong(id_s);
        System.out.println("id:" + id);
        baseResponse = adminService.getUser(id);
        renderJson(baseResponse);
    }

    /**
     * 删除用户
     */
    public void deleteAdmin(){
        String id_s =  getPara("id");
        long id = Long.parseLong(id_s);
        System.out.println(id);
        baseResponse = adminService.deleteUser(id);
        renderJson(baseResponse);
    }

    /**
     * @param user
     * 更新用户信息
     */
    public void updateAdmin(@Para("") Admin user){
        System.out.println(user);
        String salt = user.getSalt();
        String password = HashKit.sha256(salt + user.getPassword());
        user.setPassword(password);
        baseResponse = adminService.updateUser(user);
        renderJson(baseResponse);
    }


    /**
     * 增加用户，注册
     */
    public void addAdmin(){
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
        baseResponse = adminService.addUser(photoId, url, account, mobile, email, password, salt, status);
        renderJson(baseResponse);

    }
}
