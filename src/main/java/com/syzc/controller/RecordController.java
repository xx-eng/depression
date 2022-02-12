package com.syzc.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.syzc.Kit.BaseResponse;
import com.syzc.model.Record;
import com.syzc.service.RecordService;

import java.util.Date;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-18 06:50
 * @description
 */
public class RecordController extends Controller {
    BaseResponse baseResponse = new BaseResponse();
    RecordService recordService = new RecordService();


    /**
     * 查找所有记录
     */
    public void getAllRecords(){
        baseResponse = recordService.getAllRecords();
        renderJson(baseResponse);
    }

    /**
     * 查找某一用户所有记录
     */
    public void getUserRecords(){
        long userId = getParaToLong("userId");
        baseResponse = recordService.getUserRecords(userId);
        renderJson(baseResponse);
    }

    /**
     * 查找某一天的记录
     */
    public void getDateRecord(){
        Date theDate = getParaToDate("theDate");
        baseResponse = recordService.getDateRecord(theDate);
        renderJson(baseResponse);
    }



    /**
     * 查找某一记录
     */
    public void getRecord(){
        long id = getParaToLong("id");
        baseResponse = recordService.getRecord(id);
        renderJson(baseResponse);
    }

    /**
     * 删除问题
     */
    public void deletedRecord(){
        long id = getParaToLong("id");
        baseResponse = recordService.deletedRecord(id);
        renderJson(baseResponse);
    }

    /**
     * @param record
     * 更改问题
     */
    public void updateRecord(@Para("") Record record){
        baseResponse = recordService.updateRecord(record);
        renderJson(baseResponse);
    }

    /**
     * 添加问题
     */
    public void addRecord(){
        long userId = getParaToLong("userId");
        int sleep = getParaToInt("sleep");
        int days = getParaToInt("days");
        int emotion = getParaToInt("emotion");
        int medicineDays = getParaToInt("medicineDays");
        int status = getParaToInt("status");
        Date theDate = getParaToDate("theDate");
        baseResponse = recordService.addRecord(userId, sleep, days, emotion, medicineDays, status, theDate);
        renderJson(baseResponse);
    }
}
