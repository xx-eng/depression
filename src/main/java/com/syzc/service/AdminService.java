package com.syzc.service;

import com.jfinal.kit.HashKit;
import com.syzc.Kit.BaseResponse;
import com.syzc.Kit.ResultCodeEnum;
import com.syzc.model.Admin;

import java.math.BigInteger;
import java.util.List;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 15:34
 * @description
 */
public class AdminService {
    BaseResponse baseResponse = new BaseResponse();
    private Admin adminDao = new Admin().dao();


    public BaseResponse getUser(long id) {
        Admin user = adminDao.findById(id);
        if(user != null){
            baseResponse.setData(user);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse deleteUser(long id) {
        Admin user = adminDao.findById(id);
        if(user != null){
            user.setIsDeleted(1);
            boolean flag = user.update();
            if(flag){
                baseResponse.setData(user);
                baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_DELETE_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse updateUser(Admin user) {
        if(user != null){
            boolean flag = user.update();
            if(flag){
                baseResponse.setData(user);
                baseResponse.setResult(ResultCodeEnum.DB_UPATE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse addUser(long photoId,String url,String account, String mobile,String email, String password,String salt, int status) {
        Admin user = new Admin();
        user.setAccount(account); user.setMobile(mobile);
        user.setPassword(password); user.setSalt(salt);
        if(user != null){
            boolean flag = user.save();
            if(flag){
                baseResponse.setData(user);
                baseResponse.setResult(ResultCodeEnum.DB_ADD_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse login(String mobile, String password) {
        Admin user = adminDao.findFirst("select * from user where mobile = ? ", mobile);
        if(user != null){
            String passTrue = user.getPassword();
            String salt = user.getSalt();
            if(HashKit.sha256(password + salt).equals( passTrue)){
                baseResponse.setResult(ResultCodeEnum.LOGIN_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.LOGIN_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.LOGIN_NULL);
        }
        return baseResponse;
    }
}
