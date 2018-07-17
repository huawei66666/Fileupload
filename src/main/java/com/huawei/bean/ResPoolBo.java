package com.huawei.bean;


import java.io.Serializable;

/**
 * 功能：ResPool -BO(business object)业务对象
 * 作者：yk(yanke@lianj.com)
 * 日期：2018-07-04
 * 版权所有：广东联结网络技术有限公司 版权所有(C) 2018
 */
public class ResPoolBo extends BaseBo implements Serializable {

    private static final long serialVersionUID = -622229976534939493L;

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 资源名称
     */
    private String resName;
    /**
     * 联系人
     */
    private String adminName;
    /**
     * 联系人手机号
     */
    private String adminPhone;
    /**
     * 业务地区
     */
    private String regionCode;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建者
     */
    private Long userId;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 更新时间
     */
    private java.util.Date updateTime;
    /**
     * 数据状态【0 删除 1 可用】
     */
    private Integer dataStatus;
    /**
     * 业务跟进状态【0 未跟进 1已跟进】
     */
    private Integer flowStatus;
    /**
     * 业务状态跟进描述
     */
    private String flowComment;
    /**
     * 数据来源【0 手动添加 1：excel 导入 2：crm 导入 3，收藏添加】
     */
    private Integer source;
    /**
     * 数据客户端来源
     */
    private Integer sourceClient;
    /**
     * 厂商类型
     */
    private String resType;
    /**
     * 主营类目
     */
    private String mainBiz;
    /**
     * 成立时间
     */
    private String establishTime;
    /**
     * 注册资本
     */
    private String capital;
    /**
     * 员工人数
     */
    private String staffCount;
    /**
     * 厂房面积
     */
    private String plantArea;
    /**
     * 年营业额
     */
    private String turnOver;
    /**
     * 资源ID
     */
    private Long resId;
    /**
     * 归属资源库分类
     */
    private String belongClassify;
    /**
     * 归属资源库分类Node
     */
    private String belongClassifyNode;
    /**
     * exId
     */
    private Long exId;
    /**
     * exSubject
     */
    private String exSubject;
    /**
     * exCode
     */
    private String exCode;
    /**
     * CRM用户ID
     */
    private Long crmUserId;
    /**
     * 是否入驻资源【0 为入驻资源 >=1入驻过资源】
     */
    private Integer resCount;
    /**
     * 业务地区名称【非数据库字段】
     */
    private String regionName;

    public ResPoolBo() {
    }

    public ResPoolBo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public ResPoolBo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getResName() {
        return this.resName;
    }

    public ResPoolBo setResName(String resName) {
        this.resName = resName;
        return this;
    }

    public String getAdminName() {
        return this.adminName;
    }

    public ResPoolBo setAdminName(String adminName) {
        this.adminName = adminName;
        return this;
    }

    public String getAdminPhone() {
        return this.adminPhone;
    }

    public ResPoolBo setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
        return this;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public ResPoolBo setRegionCode(String regionCode) {
        this.regionCode = regionCode;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public ResPoolBo setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getRemark() {
        return this.remark;
    }

    public ResPoolBo setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Long getUserId() {
        return this.userId;
    }

    public ResPoolBo setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public ResPoolBo setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public ResPoolBo setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Integer getDataStatus() {
        return this.dataStatus;
    }

    public ResPoolBo setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
        return this;
    }

    public Integer getFlowStatus() {
        return this.flowStatus;
    }

    public ResPoolBo setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
        return this;
    }

    public String getFlowComment() {
        return this.flowComment;
    }

    public ResPoolBo setFlowComment(String flowComment) {
        this.flowComment = flowComment;
        return this;
    }

    public Integer getSource() {
        return this.source;
    }

    public ResPoolBo setSource(Integer source) {
        this.source = source;
        return this;
    }

    public Integer getSourceClient() {
        return this.sourceClient;
    }

    public ResPoolBo setSourceClient(Integer sourceClient) {
        this.sourceClient = sourceClient;
        return this;
    }

    public String getResType() {
        return this.resType;
    }

    public ResPoolBo setResType(String resType) {
        this.resType = resType;
        return this;
    }

    public String getMainBiz() {
        return this.mainBiz;
    }

    public ResPoolBo setMainBiz(String mainBiz) {
        this.mainBiz = mainBiz;
        return this;
    }

    public String getEstablishTime() {
        return this.establishTime;
    }

    public ResPoolBo setEstablishTime(String establishTime) {
        this.establishTime = establishTime;
        return this;
    }

    public String getCapital() {
        return this.capital;
    }

    public ResPoolBo setCapital(String capital) {
        this.capital = capital;
        return this;
    }

    public String getStaffCount() {
        return this.staffCount;
    }

    public ResPoolBo setStaffCount(String staffCount) {
        this.staffCount = staffCount;
        return this;
    }

    public String getPlantArea() {
        return this.plantArea;
    }

    public ResPoolBo setPlantArea(String plantArea) {
        this.plantArea = plantArea;
        return this;
    }

    public String getTurnOver() {
        return this.turnOver;
    }

    public ResPoolBo setTurnOver(String turnOver) {
        this.turnOver = turnOver;
        return this;
    }

    public Long getResId() {
        return this.resId;
    }

    public ResPoolBo setResId(Long resId) {
        this.resId = resId;
        return this;
    }

    public String getBelongClassify() {
        return this.belongClassify;
    }

    public ResPoolBo setBelongClassify(String belongClassify) {
        this.belongClassify = belongClassify;
        return this;
    }

    public String getBelongClassifyNode() {
        return this.belongClassifyNode;
    }

    public ResPoolBo setBelongClassifyNode(String belongClassifyNode) {
        this.belongClassifyNode = belongClassifyNode;
        return this;
    }

    public Long getExId() {
        return this.exId;
    }

    public ResPoolBo setExId(Long exId) {
        this.exId = exId;
        return this;
    }

    public String getExSubject() {
        return this.exSubject;
    }

    public ResPoolBo setExSubject(String exSubject) {
        this.exSubject = exSubject;
        return this;
    }

    public String getExCode() {
        return this.exCode;
    }

    public ResPoolBo setExCode(String exCode) {
        this.exCode = exCode;
        return this;
    }

    public Long getCrmUserId() {
        return this.crmUserId;
    }

    public ResPoolBo setCrmUserId(Long crmUserId) {
        this.crmUserId = crmUserId;
        return this;
    }

    public Integer getResCount() {
        return this.resCount;
    }

    public ResPoolBo setResCount(Integer resCount) {
        this.resCount = resCount;
        return this;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public ResPoolBo(String resName, String adminName, String adminPhone, String regionCode) {
        this.resName = resName;
        this.adminName = adminName;
        this.adminPhone = adminPhone;
        this.regionCode = regionCode;
    }
}

