package com.syzc.service;

import com.syzc.Kit.BaseResponse;
import com.syzc.Kit.ResultCodeEnum;
import com.syzc.model.Posts;
import com.syzc.model.User;
import javafx.geometry.Pos;

import java.math.BigInteger;
import java.util.List;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 17:39
 * @description
 */
public class PostsService {
    BaseResponse baseResponse = new BaseResponse();
    Posts postsDao = new Posts().dao();
    User userDao = new User().dao();

    public BaseResponse getAllPosts() {
        List<Posts> posts = postsDao.find("select * from posts where is_deleted = 0");
        if(!posts.isEmpty()){
            for(Posts post: posts ){
                long userId = post.getUserId().longValue();
                User user = userDao.findById(userId);
                post.put("account",user.getAccount());
                post.put("url", user.getUrl());
            }
            baseResponse.setData(posts);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getPost(long id) {
        Posts posts = postsDao.findById(id);
        long userId = posts.getUserId().longValue();
        User user = userDao.findById(userId);
        posts.put("account",user.getAccount());
        posts.put("url", user.getUrl());
        if(posts != null){
            baseResponse.setData(posts);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse deletedPosts(long id) {
        Posts posts = postsDao.findById(id);
        if(posts != null){
            posts.setIsDeleted(1);
            boolean flag = posts.update();
            if(flag){
                baseResponse.setData(posts);
                baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse updatePosts(Posts posts) {
        if(posts != null){
            boolean flag = posts.update();
            if(flag){
                baseResponse.setData(posts);
                baseResponse.setResult(ResultCodeEnum.DB_UPATE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse addPosts(long userId, String content,int likes, int comments, int status) {
        Posts posts = new Posts();
        posts.setUserId(BigInteger.valueOf(userId));
        posts.setContent(content);
        posts.setLikes(likes);
        posts.setComments(comments);
        posts.setStatus(status);
        if(posts != null){
            boolean flag = posts.save();
            if(flag){
                baseResponse.setData(posts);
                baseResponse.setResult(ResultCodeEnum.DB_ADD_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getUserPosts(long userId) {
        List<Posts> posts = postsDao.find("select * from posts where is_deleted = 0 and user_id = ?", userId);
        if(!posts.isEmpty()){
            for(Posts post: posts ){
                User user = userDao.findById(userId);
                post.put("account",user.getAccount());
                post.put("url", user.getUrl());
            }
            baseResponse.setData(posts);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse addLikes(long id) {
        Posts posts = postsDao.findById(id);
        if(posts != null){
            int likes = posts.getLikes();
            posts.setLikes(likes + 1);
            boolean flag = posts.update();
            if(flag){
                baseResponse.setData(posts);
                baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }
}
