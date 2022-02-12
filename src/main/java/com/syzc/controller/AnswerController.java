package com.syzc.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.sun.org.glassfish.gmbal.ParameterNames;
import com.syzc.Kit.BaseResponse;
import com.syzc.model.Answer;
import com.syzc.service.AnswerService;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 18:48
 * @description
 */
public class AnswerController extends Controller {
    BaseResponse baseResponse = new BaseResponse();
    AnswerService answerService = new AnswerService();


    /**
     * 查找所有答案
     */
    public void getAllAnswer(){
        baseResponse = answerService.getAllAnswer();
        renderJson(baseResponse);
    }

    /**
     * 查找某一用户所有答案
     */
    public void getUserAnswer(){
        long userId = getParaToLong("userId");
        baseResponse = answerService.getUserAnswer(userId);
        renderJson(baseResponse);
    }

    /**
     * 查找某一问题所有答案
     */
    public void getQAnswer(){
        long questionId = getParaToLong("questionId");
        baseResponse = answerService.getQAnswer(questionId);
        renderJson(baseResponse);
    }



    /**
     * 查找某一答案
     */
    public void getAnswer(){
        long id = getParaToLong("id");
        baseResponse = answerService.getAnswer(id);
        renderJson(baseResponse);
    }

    /**
     * 删除答案
     */
    public void deletedAnswer(){
        long id = getParaToLong("id");
        baseResponse = answerService.deletedAnswer(id);
        renderJson(baseResponse);
    }

    /**
     * @param answer
     * 更改答案
     */
    public void updateAnswer(@Para("") Answer answer){
        baseResponse = answerService.updateAnswer(answer);
        renderJson(baseResponse);
    }

    /**
     * 添加答案
     */
    public void addAnswer(){
        long userId = getParaToLong("userId");
        long questionIdd = getParaToLong("questionId");
        String answer = getPara("answer");
        int likes = 0;
        baseResponse = answerService.addAnswer(userId, questionIdd, answer, likes);
        renderJson(baseResponse);
    }

    /**
     * 点赞
     */
    public void addLikes(){
        long id = getParaToLong("id");
        baseResponse = answerService.addLikes(id);
        renderJson(baseResponse);
    }
}
