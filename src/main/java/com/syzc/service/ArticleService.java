package com.syzc.service;

import com.syzc.Kit.BaseResponse;
import com.syzc.Kit.ResultCodeEnum;
import com.syzc.model.Article;

import java.math.BigInteger;
import java.util.List;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 15:48
 * @description
 */
public class ArticleService {
    BaseResponse baseResponse = new BaseResponse();
    Article articleDao = new Article().dao();

    public BaseResponse getAllArticles() {
        List<Article> articles = articleDao.find("select * from article where is_deleted = 0");
        if(!articles.isEmpty()){
            baseResponse.setData(articles);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getArticle(long id) {
        Article article = articleDao.findById(id);
        if(article != null){
            baseResponse.setData(article);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse deleteArticle(long id) {
        Article article = articleDao.findById(id);
        if(article != null){
           article.setIsDeleted(1);
           boolean flag = article.update();
           if(flag){
               baseResponse.setData(article);
               baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
           }else{
               baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
           }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse updateArticle(Article article) {
        if(article != null){
            boolean flag = article.update();
            if(flag){
                baseResponse.setData(article);
                baseResponse.setResult(ResultCodeEnum.DB_UPATE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse addTextArticle(long userId, int type, String title, String content, int likes) {
        Article article = new Article();
        article.setUserId(BigInteger.valueOf(userId));
        article.setType(type); article.setTitle(title);
        article.setContent(content);  article.setLikes(likes);
        if(article != null){
            boolean flag = article.save();
            if(flag){
                baseResponse.setData(article);
                baseResponse.setResult(ResultCodeEnum.DB_ADD_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse addPicArticle(long userId, int type, String title, long fileId, String url, int likes) {
        Article article = new Article();
        article.setUserId(BigInteger.valueOf(userId));
        article.setType(type); article.setTitle(title);
        article.setFileId(fileId);  article.setUrl(url);
        article.setLikes(likes);
        if(article != null){
            boolean flag = article.save();
            if(flag){
                baseResponse.setData(article);
                baseResponse.setResult(ResultCodeEnum.DB_ADD_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getUserArticles(long userId) {
        List<Article> questions = articleDao.find("select * from article where is_deleted = 0 and user_id = ?", userId);
        if(!questions.isEmpty()){
            baseResponse.setData(questions);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse addArticle(long userId, int type, String title, String content, long fileId, String url, int likes) {
        Article article = new Article();
        article.setUserId(BigInteger.valueOf(userId));
        article.setType(type); article.setTitle(title);
        article.setFileId(fileId); article.setUrl(url);
        article.setContent(content);  article.setLikes(likes);
        if(article != null){
            boolean flag = article.save();
            if(flag){
                baseResponse.setData(article);
                baseResponse.setResult(ResultCodeEnum.DB_ADD_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse addLikes(long id) {
        Article article = articleDao.findById(id);
        if(article != null){
            int likes = article.getLikes();
            article.setLikes(likes + 1);
            boolean flag = article.update();
            if(flag){
                baseResponse.setData(article);
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
