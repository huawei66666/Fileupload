/**
 * 
 */
package com.huawei.bean;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Project:			<p>
 * <p>Module:			<p>
 * <p>Description:		<p>
 *
 * @author Jonson Xia
 * @date 2015年8月14日 下午2:40:49
 */
public class PageData<T> implements Serializable {

	/**  */
	private static final long serialVersionUID = 7583013970905172965L;

	private Integer totalNum;

	private Integer totalPage;

	private Integer pageSize;

	private Integer pageNO = 1;

	private List<T> list;

	public PageData() {
	}

	public PageData(Integer pageNO, Integer pageSize) {
		this.pageNO = pageNO;
		this.pageSize = pageSize;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public PageData setTotalNum( Integer totalNum ) {
		this.totalNum = totalNum;
		return this;
	}

	public PageData setTotalNum( long totalNum ) {
		this.totalNum = Long.valueOf(totalNum).intValue();
		return this;
	}

	public Integer getTotalPage() {
		if(this.totalPage == null && this.totalNum != null){
			if(this.totalNum < 0L) {
				this.totalPage = 0;
			} else {
				int count = this.totalNum / this.pageSize;
				if(this.totalNum % this.pageSize > 0L) {
					++count;
				}
				this.totalPage = count;
			}
		}
		return totalPage;
	}

	public PageData setTotalPage( Integer totalPage ) {
		this.totalPage = totalPage;
		return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public PageData setPageSize( Integer pageSize ) {
		this.pageSize = pageSize;
		return this;
	}

	public Integer getPageNO() {
		return pageNO;
	}

	public PageData setPageNO( Integer pageNO ) {
		this.pageNO = pageNO;
		return this;
	}

	public List<T> getList() {
		return list;
	}

	public PageData setList( List<T> list ) {
		this.list = list;
		return this;
	}

}
