package com.syzc.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRecord<M extends BaseRecord<M>> extends Model<M> implements IBean {

	public void setId(java.math.BigInteger id) {
		set("id", id);
	}
	
	public java.math.BigInteger getId() {
		return get("id");
	}
	
	public void setUserId(java.math.BigInteger userId) {
		set("user_id", userId);
	}
	
	public java.math.BigInteger getUserId() {
		return get("user_id");
	}
	
	public void setSleep(java.lang.Integer sleep) {
		set("sleep", sleep);
	}
	
	public java.lang.Integer getSleep() {
		return getInt("sleep");
	}
	
	public void setDays(java.lang.Integer days) {
		set("days", days);
	}
	
	public java.lang.Integer getDays() {
		return getInt("days");
	}
	
	public void setEmotion(java.lang.Integer emotion) {
		set("emotion", emotion);
	}
	
	public java.lang.Integer getEmotion() {
		return getInt("emotion");
	}
	
	public void setMedicineDays(java.lang.Integer medicineDays) {
		set("medicine_days", medicineDays);
	}
	
	public java.lang.Integer getMedicineDays() {
		return getInt("medicine_days");
	}
	
	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}
	
	public void setTheDate(java.util.Date theDate) {
		set("theDate", theDate);
	}
	
	public java.util.Date getTheDate() {
		return getDate("theDate");
	}
	
	public void setIsDeleted(java.lang.Integer isDeleted) {
		set("is_deleted", isDeleted);
	}
	
	public java.lang.Integer getIsDeleted() {
		return getInt("is_deleted");
	}
	
	public void setCreatedTime(java.util.Date createdTime) {
		set("CREATED_TIME", createdTime);
	}
	
	public java.util.Date getCreatedTime() {
		return getDate("CREATED_TIME");
	}
	
	public void setUpdatedTime(java.util.Date updatedTime) {
		set("UPDATED_TIME", updatedTime);
	}
	
	public java.util.Date getUpdatedTime() {
		return getDate("UPDATED_TIME");
	}
	
}

