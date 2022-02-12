package com.syzc.service;

import com.syzc.Kit.BaseResponse;
import com.syzc.Kit.ResultCodeEnum;
import com.syzc.model.Record;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-18 06:50
 * @description
 */
public class RecordService {

    BaseResponse baseResponse = new BaseResponse();
    Record recordDao = new Record().dao();

    public BaseResponse getAllRecords() {
        List<Record> records = recordDao.find("select * from record where is_deleted = 0");
        if(!records.isEmpty()){
            baseResponse.setData(records);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getRecord(long id) {
        Record record = recordDao.findById(id);
        if(record != null){
            baseResponse.setData(record);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse deletedRecord(long id) {
        Record record = recordDao.findById(id);
        if(record != null){
            record.setIsDeleted(1);
            boolean flag = record.update();
            if(flag){
                baseResponse.setData(record);
                baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_DELETE_SUCCESS);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse updateRecord(Record record) {
        if(record != null){
            boolean flag = record.update();
            if(flag){
                baseResponse.setData(record);
                baseResponse.setResult(ResultCodeEnum.DB_UPATE_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_UPDATE_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse addRecord(long userId, int sleep, int days, int emotion, int medicineDays, int status, Date theDate) {
        Record record = new Record();
        record.setUserId(BigInteger.valueOf(userId)); record.setSleep(sleep); record.setDays(days);
        record.setEmotion(emotion); record.setMedicineDays(medicineDays);
        record.setStatus(status); record.setTheDate(theDate);
        if(record != null){
            boolean flag = record.save();
            if(flag){
                baseResponse.setData(record);
                baseResponse.setResult(ResultCodeEnum.DB_ADD_SUCCESS);
            }else{
                baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
            }
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_ADD_FAILURE);
        }
        return baseResponse;
    }

    public BaseResponse getUserRecords(long userId) {
        List<Record> records = recordDao.find("select * from record where is_deleted = 0 and user_id = ?", userId);
        if(!records.isEmpty()){
            baseResponse.setData(records);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }


    public BaseResponse getDateRecord(Date theDate) {
        Record record1 = recordDao.findFirst("select * from record where is_deleted = 0 and theDate = ?",theDate);
        if(record1 != null){
            baseResponse.setData(record1);
            baseResponse.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
        }else{
            baseResponse.setResult(ResultCodeEnum.DB_FIND_FAILURE);
        }
        return baseResponse;
    }
}
