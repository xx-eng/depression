package com.syzc.service;

import com.syzc.Kit.BaseResponse;
import com.syzc.Kit.ResultCodeEnum;
import com.syzc.model.Comment;
import com.syzc.model.User;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.List;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 17:09
 * @description
 */
public class CommentService {
    BaseResponse baseResponse = new BaseResponse();
    Comment commentDao = new Comment().dao();
    User userDao = new User().dao();

    public BaseResponse getAllComments() {
        List<Comment> comments = commentDao.find("select * from comment where is_deleted = 0");
        if(!comments.isEmpty()){
            for(Comment comment: comments ){
                long userId = comment.getUserId().longValue();
                User user = userDao.findById(userId);
                comment.put("account",user.getAccount());
                comment.put("url", user.getUrl());
            }
            baseResponse.setData(comments);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getComment(long id) {
        Comment comment = commentDao.findById(id);
        long userId = comment.getUserId().longValue();
        User user = userDao.findById(userId);
        comment.put("account",user.getAccount());
        comment.put("url", user.getUrl());
        if(comment != null){
            baseResponse.setData(comment);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse deleteComment(long id) {
        Comment comment = commentDao.findById(id);
        if(comment != null){
            comment.setIsDeleted(1);
            boolean flag = comment.update();
            if(flag){
                baseResponse.setData(comment);
                baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse updateComment(Comment comment) {
        if(comment != null){
            boolean flag = comment.update();
            if(flag){
                baseResponse.setData(comment);
                baseResponse.setResult(ResultCodeEnum.DB_UPATE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse addComment(long userId, long criticId, long PostsId, String content) {
        Comment comment = new Comment();
        comment.setUserId(BigInteger.valueOf(userId));
        comment.setCriticId(BigInteger.valueOf(criticId));
        comment.setPostsId(BigInteger.valueOf(PostsId));
        comment.setContent(content);
        //这里要增加动态里面的评论数！！！
        if(comment != null){
            boolean flag = comment.save();
            if(flag){
                baseResponse.setData(comment);
                baseResponse.setResult(ResultCodeEnum.DB_ADD_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getUserComments(long criticId) {
        List<Comment> comments = commentDao.find("select * from comment where is_deleted = 0 and critic_id = ?", criticId);
        if(!comments.isEmpty()){
            for(Comment comment: comments ){
                long userId = comment.getUserId().longValue();
                User user = userDao.findById(userId);
                comment.put("account",user.getAccount());
                comment.put("url", user.getUrl());
            }
            baseResponse.setData(comments);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getPostComments(long postsId) {
        List<Comment> comments = commentDao.find("select * from comment where is_deleted = 0 and posts_id = ?", postsId);
        if(!comments.isEmpty()){
            for(Comment comment: comments ){
                long userId = comment.getUserId().longValue();
                User user = userDao.findById(userId);
                comment.put("account",user.getAccount());
                comment.put("url", user.getUrl());
            }
            baseResponse.setData(comments);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }
}
