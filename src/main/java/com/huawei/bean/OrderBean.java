package com.huawei.bean;

import java.io.Serializable;

/**
 * 功能：排序
 * 作者：Jimbo(Jimbo@lianj.com)
 * 日期：2016-06-03
 * 版权所有：广东联结网络技术有限公司 版权所有(C) 2016
 */
public class OrderBean implements Serializable {

    private static final long serialVersionUID = 2300246956380433188L;

    /**
     * DB排序方式
     */
    private String order;
    /**
     * 排序字段
     */
    private String orderBy;

    public OrderBean() {
    }

    public OrderBean(String order, String orderBy) {
        this.order = order;
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
