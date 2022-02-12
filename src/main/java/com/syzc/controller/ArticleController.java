package com.syzc.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.LongGetter;
import com.jfinal.core.paragetter.Para;
import com.syzc.Kit.BaseResponse;
import com.syzc.Kit.ResultCodeEnum;
import com.syzc.model.Article;
import com.syzc.service.ArticleService;

import java.util.List;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 15:48
 * @description
 */
public class ArticleController extends Controller {
    BaseResponse baseResponse = new BaseResponse();
    ArticleService articleService = new ArticleService();

    /**
     * 查找所有文章
     */
    public void getAllArticle(){
       baseResponse = articleService.getAllArticles();
       renderJson(baseResponse);
    }

    /**
     * 查找某一用户所有问题
     */
    public void getUserArticles(){
        long userId = getParaToLong("userId");
        baseResponse = articleService.getUserArticles(userId);
        renderJson(baseResponse);
    }

    /**
     * 查找某一文章
     */
    public void getArticle(){
        long id = getParaToLong("id");
        baseResponse = articleService.getArticle(id);
        renderJson(baseResponse);
    }

    /**
     * 删除文章
     */
    public void deletedArticle(){
        long id = getParaToLong("id");
        baseResponse = articleService.deleteArticle(id);
        renderJson(baseResponse);
    }

    /**
     * @param article
     * 更改文章
     */
    public void updateArticle(@Para("")Article article){
        System.out.println("article"+article.getContent());
        baseResponse = articleService.updateArticle(article);
        renderJson(baseResponse);
    }


    /**
     * 添加文本文章
     */
    public void addArticle(){
        System.out.println("添加文章");
        String userId_str = getPara("userId");
        long userId = Long.valueOf(userId_str);
        int type = getParaToInt("type");
        String title = getPara("title");
        String content = getPara("content");
        long fileId = getParaToLong("fileId");
        String url = getPara("url");
        int likes = 0;
        baseResponse = articleService.addArticle(userId, type, title, content, fileId, url, likes);
        renderJson(baseResponse);
    }

    /**
     * 点赞
     */
    public void addLikes(){
        long id = getParaToLong("id");
        baseResponse = articleService.addLikes(id);
        renderJson(baseResponse);
    }


    /**
     * 添加文本文章
     */
    public void addTextArticle(){
        long userId = getParaToLong("userId");
        int type = 0;
        String title = getPara("title");
        String content = getPara("content");
        int likes = 0;
        baseResponse = articleService.addTextArticle(userId, type, title, content, likes);
        renderJson(baseResponse);
    }

    /**
     * 添加图片文章
     */
    public void addPicArticle(){
        long userId = getParaToLong("userId");
        int type = 1;
        String title = getPara("title");
        long fileId = getParaToLong("fileId");
        String url = getPara("url");
        int likes = 0;
        baseResponse = articleService.addPicArticle(userId, type, title, fileId,url, likes);
        renderJson(baseResponse);
    }
}
