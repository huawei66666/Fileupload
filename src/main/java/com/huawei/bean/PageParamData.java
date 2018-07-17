package com.huawei.bean;

import java.io.Serializable;

/**
 * 分页请求参数封装
 * 
 * <p>Project:			<p>
 * <p>Module:			<p>
 * <p>Description:		<p>
 *
 * @author 向朗
 * @date 2015年10月27日 下午2:15:15
 */
public class PageParamData<T> implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;

	private Integer pageNo;

	private Integer pageSize;

	private T data;

	public Integer getPageNo() {
		return pageNo;
	}

	public PageParamData setPageNo( Integer pageNo ) {
		this.pageNo = pageNo;
		return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public PageParamData setPageSize( Integer pageSize ) {
		this.pageSize = pageSize;
		return this;
	}

	public T getData() {
		return data;
	}

	public PageParamData setData( T data ) {
		this.data = data;
		return this;
	}

}
