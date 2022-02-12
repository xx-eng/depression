package com.syzc.service;

import com.syzc.Kit.BaseResponse;
import com.syzc.Kit.ResultCodeEnum;
import com.syzc.model.Question;

import java.math.BigInteger;
import java.util.List;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 17:55
 * @description
 */
public class QuestionService {
    BaseResponse baseResponse = new BaseResponse();
    Question questionDao = new Question().dao();

    public BaseResponse getAllQuestions() {
        List<Question> questions = questionDao.find("select * from question where is_deleted = 0");
        if(!questions.isEmpty()){
            baseResponse.setData(questions);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getQuestion(long id) {
        Question question = questionDao.findById(id);
        if(question != null){
            baseResponse.setData(question);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse deletedQuestion(long id) {
        Question question = questionDao.findById(id);
        if(question != null){
            question.setIsDeleted(1);
            boolean flag = question.update();
            if(flag){
                baseResponse.setData(question);
                baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse updateQuetion(Question question) {
        if(question != null){
            boolean flag = question.update();
            if(flag){
                baseResponse.setData(question);
                baseResponse.setResult(ResultCodeEnum.DB_UPATE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse addQuestion(long userId,String title, String content) {
        Question question = new Question();
        question.setUserId(BigInteger.valueOf(userId));
        question.setTitle(title);
        question.setContent(content);
        if(question != null){
            boolean flag = question.save();
            if(flag){
                baseResponse.setData(question);
                baseResponse.setResult(ResultCodeEnum.DB_ADD_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getUserQuestions(long userId) {
        List<Question> questions = questionDao.find("select * from question where is_deleted = 0 and user_id = ?", userId);
        if(!questions.isEmpty()){
            baseResponse.setData(questions);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }



}
