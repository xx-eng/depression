package com.syzc.Kit;

import com.jfinal.kit.JsonKit;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-16 14:56
 * @description
 */
public class BaseResponse {
    private Object data;
    private String resultCode;

    public String getResultCode() {
        return resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    private String resultDesc;

    public Object getData(){
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public void setResult(ResultCodeEnum code){
        this.resultCode = code.getCode();
        this.resultDesc = code.getDesc();
    }

    //返回json
    @Override
    public String toString(){
        return JsonKit.toJson(this);
    }
}
