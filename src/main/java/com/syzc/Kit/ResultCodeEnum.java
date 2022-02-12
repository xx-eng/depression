package com.syzc.Kit;

/**
 * @author xueli
 * @email 2632624281@qq.com
 * @date 2022-01-16 15:00
 * @description
 */
public enum ResultCodeEnum {
    DB_FIND_SUCCESS("2000","数据库查找成功"),
    DB_UPATE_SUCCESS("2001","数据库更新成功"),
    DB_DELETE_SUCCESS("2002","数据库删除成功"),
    DB_ADD_SUCCESS("2003","数据库增加成功"),
    DB_FIND_FAILURE("2007","数据库查找失败，没有该条记录"),
    DB_UPDATE_FAILURE("2008","数据库更新失败"),
    DB_DELETE_FAILURE("2009","数据库删除失败"),
    DB_ADD_FAILURE("2010","数据库增加失败"),
    LOGIN_SUCCESS("0001","登录成功"),
    LOGIN_FAILURE("0000","登录失败"),
    LOGIN_NULL("0002","该用户不存在");

    private String code;
    private String desc;
    ResultCodeEnum(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
    public String getCode()
    {
        return code;
    }
    public String getDesc()
    {
        return desc;
    }
}
