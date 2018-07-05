package com.huawei.utils;

import java.io.Serializable;

/**
 *  API 返回对象
 * @author xuhaowen
 */
public class ApiResult implements Serializable {
	
	private static final long serialVersionUID = -8309041712846444484L;
	
	private Boolean success = false;
	private String msg;
	private Object obj;
	
	public ApiResult(boolean success, String msg){
		this.success = success;
		this.msg = msg;
	}
	
	public ApiResult(boolean success, String msg, Object obj){
		this.success = success;
		this.msg = msg;
		this.obj = obj;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
