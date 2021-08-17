package com.longfor.fsscreportdb.ods.entity;

import java.math.BigDecimal;

public class OdsHrDirct {
    private Long id;
    private String oaccount;
    private String orgId;
    private String orgName;
    private String orgDirct;
    private String employee;
    private String channelNm;
    private String areaName;
    private BigDecimal amount;
    private String regionName;
    private String regionId;
    private String orderName;
    private String groupId;

    public OdsHrDirct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    private String deptName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOaccount() {
        return oaccount;
    }

    public void setOaccount(String oaccount) {
        this.oaccount = oaccount;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDirct() {
        return orgDirct;
    }

    public void setOrgDirct(String orgDirct) {
        this.orgDirct = orgDirct;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getChannelNm() {
        return channelNm;
    }

    public void setChannelNm(String channelNm) {
        this.channelNm = channelNm;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "OdsHrDirct{" +
                "oaccount='" + oaccount + '\'' +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgDirct='" + orgDirct + '\'' +
                ", employee='" + employee + '\'' +
                ", channelNm='" + channelNm + '\'' +
                ", areaName='" + areaName + '\'' +
                ", amount=" + amount +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
