package com.huawei.excel;

import com.github.crab2died.annotation.ExcelField;

import java.io.Serializable;

/**
 * <p>功能： </p>
 * <p>作者：yk(yanke@lianj.com)</p>
 * <p>日期：2018年07月09日 15:49</p>
 * <p>版权所有：广东联结网络技术有限公司 版权所有(C) 2016-2018</p>
 */
public class ResPoolImportModel implements Serializable {

    /**
     * 资源名称
     */
    @ExcelField(title = "企业/劳务队伍名称")
    private String resName;

    /**
     * 联系人
     */
    @ExcelField(title = "联系人")
    private String adminName;

    /**
     * 联系人手机号
     */
    @ExcelField(title = "手机号")
    private String adminPhone;

    /**
     * 业务地区
     */
    private String regionCode;

    /**
     * 业务地区
     */
    @ExcelField(title = "地区")
    private String regionName;

    /**
     * 详细地址
     */
    @ExcelField(title = "详细地址")
    private String address;

    /**
     * 备注
     */
    @ExcelField(title = "备注")
    private String remark;


    /**
     * 厂商类型
     */
    @ExcelField(title = "厂商类型")
    private String resType;

    /**
     * 主营类目
     */
    @ExcelField(title = "主营类目")
    private String mainBiz;

    /**
     * 成立时间
     */
    @ExcelField(title = "成立时间", readConverter = DateCovert.class)
    private String establishTime;

    /**
     * 注册资本
     */
    @ExcelField(title = "注册资本")
    private String capital;

    /**
     * 员工人数
     */
    @ExcelField(title = "员工人数")
    private String staffCount;

    /**
     * 厂房面积
     */
    @ExcelField(title = "厂房面积")
    private String plantArea;

    /**
     * 年营业额
     */
    @ExcelField(title = "年营业额")
    private String turnOver;

    /**
     * 错误原因
     */
    @ExcelField(title = "错误原因")
    private String errorReason;

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getMainBiz() {
        return mainBiz;
    }

    public void setMainBiz(String mainBiz) {
        this.mainBiz = mainBiz;
    }

    public String getEstablishTime() {
        return establishTime;
    }

    public void setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getStaffCount() {
        return staffCount;
    }

    public void setStaffCount(String staffCount) {
        this.staffCount = staffCount;
    }

    public String getPlantArea() {
        return plantArea;
    }

    public void setPlantArea(String plantArea) {
        this.plantArea = plantArea;
    }

    public String getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(String turnOver) {
        this.turnOver = turnOver;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    @Override
    public String toString() {
        return "ResPoolImportModel{" +
                "resName='" + resName + '\'' +
                ", adminName='" + adminName + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", regionName='" + regionName + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", resType='" + resType + '\'' +
                ", mainBiz='" + mainBiz + '\'' +
                ", establishTime='" + establishTime + '\'' +
                ", capital='" + capital + '\'' +
                ", staffCount='" + staffCount + '\'' +
                ", plantArea='" + plantArea + '\'' +
                ", turnOver='" + turnOver + '\'' +
                ", errorReason='" + errorReason + '\'' +
                '}';
    }
}
