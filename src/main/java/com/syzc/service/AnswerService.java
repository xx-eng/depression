package com.syzc.service;

import com.syzc.Kit.BaseResponse;
import com.syzc.Kit.ResultCodeEnum;
import com.syzc.model.Answer;

import java.math.BigInteger;
import java.util.List;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 18:49
 * @description
 */
public class AnswerService {
    BaseResponse baseResponse = new BaseResponse();
    Answer answerDao = new Answer().dao();

    public BaseResponse getAllAnswer() {
        List<Answer> questions = answerDao.find("select * from answer where is_deleted = 0");
        if(!questions.isEmpty()){
            baseResponse.setData(questions);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getAnswer(long id) {
        Answer question = answerDao.findById(id);
        if(question != null){
            baseResponse.setData(question);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse deletedAnswer(long id) {
        Answer question = answerDao.findById(id);
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

    public BaseResponse updateAnswer(Answer answer) {
        if(answer != null){
            boolean flag = answer.update();
            if(flag){
                baseResponse.setData(answer);
                baseResponse.setResult(ResultCodeEnum.DB_UPATE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse addAnswer(long userId,long questionId, String answer, int likes) {
        Answer answerObject = new Answer();
        answerObject.setUserId(BigInteger.valueOf(userId));
        answerObject.setQuestionId(BigInteger.valueOf(questionId));
        answerObject.setAnswer(answer);
        answerObject.setLikes(likes);
        if(answerObject != null){
            boolean flag = answerObject.save();
            if(flag){
                baseResponse.setData(answerObject);
                baseResponse.setResult(ResultCodeEnum.DB_ADD_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getUserAnswer(long userId) {
        List<Answer> answers = answerDao.find("select * from answer where is_deleted = 0 and user_id = ?", userId);
        if(!answers.isEmpty()){
            baseResponse.setData(answers);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getQAnswer(long questionId) {
        List<Answer> answers = answerDao.find("select * from answer where is_deleted = 0 and question_id = ?", questionId);
        if(!answers.isEmpty()){
            baseResponse.setData(answers);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse addLikes(long id) {
        Answer answer = answerDao.findById(id);
        if(answer != null){
            int likes = answer.getLikes();
            answer.setLikes(likes + 1);
            boolean flag = answer.update();
            if(flag){
                baseResponse.setData(answer);
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
