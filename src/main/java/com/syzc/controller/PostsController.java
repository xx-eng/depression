package com.syzc.controller;

import com.baidu.aip.nlp.AipNlp;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.syzc.Kit.BaseResponse;
import com.syzc.model.Posts;
import com.syzc.service.PostsService;
import org.json.JSONObject;

import java.util.HashMap;

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
        String APP_ID = "25686837";
        String API_KEY = "l4WkRnFqSZjFmIGxAMKu9SwF";
        String  SECRET_KEY = "sVzezpqTpMRx836oYt5ood0q8uNv0vh9";

        AipNlp client = new AipNlp(APP_ID, API_KEY,SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        String text = content;
        HashMap<String, Object> options = new HashMap<String, Object>();

        // 情感倾向分析
        JSONObject res = client.sentimentClassify(text, options);
        System.out.println(res.toString(2));
        System.out.println(res.getJSONArray("items").getJSONObject(0).get("sentiment"));
        String  status_s = res.getJSONArray("items").getJSONObject(0).get("sentiment").toString();
        status = Integer.parseInt(status_s);

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
