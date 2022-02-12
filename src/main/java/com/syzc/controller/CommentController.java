package com.syzc.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.syzc.Kit.BaseResponse;
import com.syzc.model.Comment;
import com.syzc.service.CommentService;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-17 17:08
 * @description
 */
public class CommentController extends Controller {
    BaseResponse baseResponse = new BaseResponse();
    CommentService commentService = new CommentService();

    /**
     * 查找所有评论
     * 查找所有评论
     */
    public void getAllComments(){
        baseResponse = commentService.getAllComments();
        renderJson(baseResponse);
    }

    /**
     * 查找某一评论者所有评论
     */
    public void getUserComments(){
        long criticId = getParaToLong("criticId");
        baseResponse = commentService.getUserComments(criticId);
        renderJson(baseResponse);
    }

    /**
     * 查找某一动态所有评论
     */
    public void getPostComments(){
        long postsId = getParaToLong("postsId");
        baseResponse = commentService.getPostComments(postsId);
        renderJson(baseResponse);
    }


    /**
     * 查找某一评论
     */
    public void getComment(){
        long id = getParaToLong("id");
        baseResponse = commentService.getComment(id);
        renderJson(baseResponse);
    }

    /**
     * 删除评论
     */
    public void deletedComment(){
        long id = getParaToLong("id");
        baseResponse = commentService.deleteComment(id);
        renderJson(baseResponse);
    }

    /**
     * @param comment
     * 更改评论
     */
    public void updateComment(@Para("") Comment comment){
        baseResponse = commentService.updateComment(comment);
        renderJson(baseResponse);
    }

    /**
     * 添加评论
     */
    public void addComment(){
        long userId = getParaToLong("userId");
        long criticId = getParaToLong("criticId");
        long postsId = getParaToLong("postsId");
        String content = getPara("content");
        baseResponse = commentService.addComment(userId, criticId, postsId, content);
        renderJson(baseResponse);
    }
}
