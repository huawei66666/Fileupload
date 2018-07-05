package com.huawei.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能：资源库数据传输对象
 * 作者：laihuawei(laihuawei@lianj.com)
 * 日期：2018年07月2018/7/5日 14:46
 * 版权所有：广东联结网络技术有限公司 版权所有(C)
 */
public class ResourceDatabaseBo implements Serializable {

    private static final long serialVersionUID = 7469451130898473636L;

    /**
     * 企业/劳务队伍名称
     */
    private String teamName;

    /**
     * 联系人
     */
    private String connect;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 地区
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 厂商类型
     */
    private String manufacturer;

    /**
     * 主营类目
     */
    private String projectType;

    /**
     * 成立时间
     */
    private Date createTime;

    /**
     * 注册资本
     */
    private String regCapital;

    /**
     * 员工人数
     */
    private String employees;

    /**
     * 厂房面积
     */
    private String plantArea;

    /**
     * 年营业额
     */
    private String revenue;

    /**
     * 备注
     */
    private String remark;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public String getPlantArea() {
        return plantArea;
    }

    public void setPlantArea(String plantArea) {
        this.plantArea = plantArea;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
