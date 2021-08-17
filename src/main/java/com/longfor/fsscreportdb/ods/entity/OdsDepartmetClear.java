package com.longfor.fsscreportdb.ods.entity;

import java.math.BigDecimal;

public class OdsDepartmetClear {
    private String departmentName;
    private BigDecimal deptAmount;
    private int recordNum;

    public OdsDepartmetClear() {
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public BigDecimal getDeptAmount() {
        return deptAmount;
    }

    public void setDeptAmount(BigDecimal deptAmount) {
        this.deptAmount = deptAmount;
    }

    public int getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(int recordNum) {
        this.recordNum = recordNum;
    }

    public OdsDepartmetClear(String departmentName, BigDecimal deptAmount, int recordNum) {
        this.departmentName = departmentName;
        this.deptAmount = deptAmount;
        this.recordNum = recordNum;
    }

    @Override
    public String toString() {
        return "OdsDepartmetClear{" +
                "departmentName='" + departmentName + '\'' +
                ", deptAmount=" + deptAmount +
                ", recordNum=" + recordNum +
                '}';
    }
}
