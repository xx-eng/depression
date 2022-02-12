package com.syzc.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.syzc.Kit.BaseResponse;
import com.syzc.model.Question;
import com.syzc.service.QuestionService;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 17:55
 * @description
 */
public class QuestionController extends Controller {
    BaseResponse baseResponse = new BaseResponse();
    QuestionService questionService = new QuestionService();

    //搜索！！！函数

    /**
     * 查找所有问题
     */
    public void getAllQuestions(){
        baseResponse = questionService.getAllQuestions();
        renderJson(baseResponse);
    }

    /**
     * 查找某一用户所有问题
     */
    public void getUserQuestions(){
        long userId = getParaToLong("userId");
        baseResponse = questionService.getUserQuestions(userId);
        renderJson(baseResponse);
    }



    /**
     * 查找某一问题
     */
    public void getQuestion(){
        long id = getParaToLong("id");
        baseResponse = questionService.getQuestion(id);
        renderJson(baseResponse);
    }

    /**
     * 删除问题
     */
    public void deletedQuestion(){
        long id = getParaToLong("id");
        baseResponse = questionService.deletedQuestion(id);
        renderJson(baseResponse);
    }

    /**
     * @param question
     * 更改问题
     */
    public void updateQuetion(@Para("") Question question){
        baseResponse = questionService.updateQuetion(question);
        renderJson(baseResponse);
    }

    /**
     * 添加问题
     */
    public void addQuestion(){
        long userId = getParaToLong("userId");
        String title = getPara("title");
        String content = getPara("content");
        baseResponse = questionService.addQuestion(userId, title, content);
        renderJson(baseResponse);
    }
}
