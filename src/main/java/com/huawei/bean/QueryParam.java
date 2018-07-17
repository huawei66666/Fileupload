package com.huawei.bean;


import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能： 查询参数
 * 作者：Jimbo(Jimbo@lianj.com)
 * 日期：2016-05-29
 * 版权所有：广东联结网络技术有限公司 版权所有(C) 2016
 */
public class QueryParam implements Serializable {

    private static final long serialVersionUID = 7880010176360629642L;

    private Object paramBean;

    private PageBean pageBean;

    private OrderBean orderBean;

    private Map<String, Object> extendParams = new HashMap<String, Object>();

    public QueryParam() {

    }

    public QueryParam(Object paramBean) {
        this.paramBean = paramBean;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Object getParamBean() {
        return paramBean;
    }

    public void setParamBean(Object paramBean) {
        this.paramBean = paramBean;
    }

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public Map<String, Object> getExtendParams() {
        return extendParams;
    }

    public void addExtendParam(String extendParamKey, Object extendParamValue) {
        this.extendParams.put(extendParamKey, extendParamValue);
    }

    public void removeExtendParam(String extendParamKey) {
        this.extendParams.remove(extendParamKey);
    }

    /**
     * 设置 默认参数
     * @param pageNo
     * @param pageSize
     * @param orderBy 排序字段名
     * @param order 排序方式 {ASC/DESC}
     */
    public void setDefaultParam(int pageNo, int pageSize, String orderBy, String order) {
        setDefaultOrder(orderBy, order);
        setDefaultPage(pageNo, pageSize);
    }

    /**
     * 设置 默认排序
     * @param orderBy 排序字段名
     * @param order 排序方式 {ASC/DESC}
     */
    public void setDefaultOrder(String orderBy, String order) {
        OrderBean orderBean = getOrderBean();
        if(orderBean == null && StringUtils.isNotBlank(orderBy)){
            orderBean = new OrderBean();
            orderBean.setOrderBy(orderBy);
            orderBean.setOrder(StringUtils.isBlank(order)?"DESC":order);
            setOrderBean(orderBean);
        }else if(orderBean != null && StringUtils.isEmpty(orderBean.getOrderBy())){
            orderBean.setOrderBy(orderBy);
            orderBean.setOrder(StringUtils.isBlank(order)?"DESC":order);
            setOrderBean(orderBean);
        }
    }

    /**
     * 设置 默认分页
     * @param pageNo
     * @param pageSize
     */
    public void setDefaultPage(int pageNo, int pageSize) {
        PageBean pageBean = getPageBean();
        if(pageBean == null){
            pageBean = new PageBean(pageNo, pageSize);
            setPageBean(pageBean);
        }
    }
}
