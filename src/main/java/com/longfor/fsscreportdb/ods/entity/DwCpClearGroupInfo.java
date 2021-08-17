package com.longfor.fsscreportdb.ods.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenziyao
 * @since 2021-07-19
 */
@TableName("DW_CP_CLEAR_GROUP_INFO")
@KeySequence("DW_CP_CLEAR_GROUP_INFO_S")
public class DwCpClearGroupInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField("GROUP_ID")
    private String groupId;

    @TableField("GROUP_NAME")
    private String groupName;

    @TableField("USER_LIST")
    private String userList;

    @TableField("CHANAL")
    private String chanal;

    @TableField("AREA")
    private String area;

    @TableField("STATUS")
    private String status;

    @TableField("SEND_DATE")
    private Date sendDate;

    @TableField("MGR_LIST")
    private String mgrList;

    @TableField("DEPT_NUM")
    private BigDecimal deptNum;

    @TableField("TOTAL_AMT")
    private BigDecimal totalAmt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getUserList() {
        return userList;
    }

    public void setUserList(String userList) {
        this.userList = userList;
    }
    public String getChanal() {
        return chanal;
    }

    public void setChanal(String chanal) {
        this.chanal = chanal;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    public String getMgrList() {
        return mgrList;
    }

    public void setMgrList(String mgrList) {
        this.mgrList = mgrList;
    }
    public BigDecimal getDeptNum() {
        return deptNum;
    }

    public void setDeptNum(BigDecimal deptNum) {
        this.deptNum = deptNum;
    }
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    @Override
    public String toString() {
        return "DwCpClearGroupInfo{" +
            "id=" + id +
            ", groupId=" + groupId +
            ", groupName=" + groupName +
            ", userList=" + userList +
            ", chanal=" + chanal +
            ", area=" + area +
            ", status=" + status +
            ", sendDate=" + sendDate +
            ", mgrList=" + mgrList +
            ", deptNum=" + deptNum +
            ", totalAmt=" + totalAmt +
        "}";
    }
}
