package com.huawei.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能： 查询分页结果
 * 作者：Jimbo(Jimbo@lianj.com)
 * 日期：2016-05-29
 * 版权所有：广东联结网络技术有限公司 版权所有(C) 2016
 */
public class PageResult<B extends BaseBo> implements Serializable {

    private static final long serialVersionUID = 4198861036976638558L;

    private List<B> dataList = new ArrayList<B>();

    private PageBean pageBean;

    public PageResult() {

    }

    public PageResult(List<B> dataList, PageBean pageBean) {
        super();
        this.dataList = dataList;
        this.pageBean = pageBean;
    }

    public List<B> getDataList() {
        return dataList;
    }

    public void setDataList(List<B> dataList) {
        this.dataList = dataList;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }


    /**
     * 转出前端统一的pageData信息
     * @return
     */
    public PageData getPageData() {
        PageData pageData=new PageData();
        pageData.setPageNO(pageBean.getPageNo());
        pageData.setPageSize(pageBean.getPageSize());
        pageData.setTotalNum(new Long(pageBean.getTotalCount()).intValue());
        pageData.setTotalPage(new Long(pageBean.getTotalPages()).intValue());
        pageData.setList(getDataList());
        return pageData;
    }
}
