package com.syzc.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.syzc.Kit.BaseResponse;
import com.syzc.model.Posts;
import com.syzc.service.PostsService;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 17:39
 * @description
 */
public class PostsController extends Controller {
    BaseResponse baseResponse = new BaseResponse();
    PostsService postsService = new PostsService();

    /**
     * 查找所有动态
     */
    public void getAllPosts(){
        baseResponse = postsService.getAllPosts();
        renderJson(baseResponse);
    }

    /**
     * 查找某一用户所有动态
     */
    public void getUserPosts(){
        long userId = getParaToLong("userId");
        baseResponse = postsService.getUserPosts(userId);
        renderJson(baseResponse);
    }



    /**
     * 查找某一动态
     */
    public void getPost(){
        long id = getParaToLong("id");
        baseResponse = postsService.getPost(id);
        renderJson(baseResponse);
    }

    /**
     * 删除动态
     */
    public void deletedPosts(){
        long id = getParaToLong("id");
        baseResponse = postsService.deletedPosts(id);
        renderJson(baseResponse);
    }

    /**
     * @param posts
     * 更改动态
     */
    public void updatePosts(@Para("") Posts posts){
        baseResponse = postsService.updatePosts(posts);
        renderJson(baseResponse);
    }

    /**
     * 添加动态
     */
    public void addPosts(){
        long userId = getParaToLong("userId");
        String content = getPara("content");
        int likes = 0;
        int comments = 0;
        int status = 0;    //之后调用接口来改这个
        baseResponse = postsService.addPosts(userId, content, likes, comments, status);
        renderJson(baseResponse);
    }

    /**
     * 点赞
     */
    public void addLikes(){
        long id = getParaToLong("id");
        baseResponse = postsService.addLikes(id);
        renderJson(baseResponse);
    }
}
