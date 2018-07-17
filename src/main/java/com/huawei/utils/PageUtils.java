package com.huawei.utils;


import com.huawei.bean.PageData;

import java.util.ArrayList;

/**
 * Function:
 * User: huyh
 * Date: 2017/3/10
 * Copyright: 广东联结网络技术有限公司 版权所有(C) 2016
 */
public class PageUtils {
    private Integer pageNo = 1;
    private Integer pageSize = 10;

    /**默认pageNo = 1 , pageSize = 10
     * @param pageNo
     * @param pageSize
     */
    public PageUtils(Integer pageNo, Integer pageSize){
        this.pageNo = (pageNo == null || pageNo < 1) ? 1 : pageNo;
        this.pageSize = (pageSize == null || pageSize < 1) ? 10 : pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = (pageNo == null || pageNo < 1) ? 1 : pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = (pageSize == null || pageSize < 1) ? 10 : pageSize;
    }

    public Integer getTotalPage(Integer count){
        if(count == null) return 0;
        return (count + pageSize - 1) / pageSize;
    }

    public Integer getOffset(){
        return (pageNo - 1) * pageSize;
    }

    public PageData createPageData(Integer count){
        PageData pageData = new PageData();
        pageData.setPageNO(pageNo);
        pageData.setPageSize(pageSize);
        pageData.setTotalNum(count);
        pageData.setTotalPage(getTotalPage(count));
        pageData.setList(new ArrayList());
        return pageData;
    }
}
