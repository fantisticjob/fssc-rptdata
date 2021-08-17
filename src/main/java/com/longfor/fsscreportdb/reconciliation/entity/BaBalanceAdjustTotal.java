package com.longfor.fsscreportdb.reconciliation.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 汇总余额调节表
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-18
 */
@TableName("BA_BALANCE_ADJUST_TOTAL")
public class BaBalanceAdjustTotal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("ID")
    private BigDecimal id;

    /**
     * 平台Code
     */
    @TableField("PLAT_CODE")
    private String platCode;

    /**
     * 平台名称
     */
    @TableField("PLAT_NAME")
    private String platName;

    /**
     * 地区Code
     */
    @TableField("AREA_CODE")
    private String areaCode;

    /**
     * 地区名称
     */
    @TableField("AREA_NAME")
    private String areaName;

    /**
     * 账套Code
     */
    @TableField("ACCOUNTS_ID")
    private String accountsId;

    /**
     * 账套名称
     */
    @TableField("ACCOUNTS_NAME")
    private String accountsName;

    /**
     * 账户ID
     */
    @TableField("ACCOUNT_ID")
    private String accountId;

    /**
     * 账户名称
     */
    @TableField("ACCOUNT_NAME")
    private String accountName;

    /**
     * 航道
     */
    @TableField("CHANAL")
    private String chanal;

    /**
     * 年月
     */
    @TableField("ACCOUNT_TIME")
    private String accountTime;

    /**
     * 企业方余额
     */
    @TableField("ENTER_BALANCE")
    private BigDecimal enterBalance;

    /**
     * 银行方余额
     */
    @TableField("BANK_BALANCE")
    private BigDecimal bankBalance;

    /**
     * 未达金额
     */
    @TableField("NOT_REACHED")
    private BigDecimal notReached;

    /**
     * 企收银未收（+）
     */
    @TableField("AMOUNT1")
    private BigDecimal amount1;

    /**
     * 企付银未付（-）
     */
    @TableField("AMOUNT2")
    private BigDecimal amount2;

    /**
     * 银收企未收（-）
     */
    @TableField("AMOUNT3")
    private BigDecimal amount3;

    /**
     * 银付企未付（+）
     */
    @TableField("AMOUNT4")
    private BigDecimal amount4;

    /**
     * 启用日期
     */
    @TableField("START_DATE")
    private Date startDate;

    /**
     * 已提交账户数量
     */
    @TableField("ACCOUNT_COMMIT")
    private BigDecimal accountCommit;

    /**
     * 总账户数量
     */
    @TableField("ACCOUNT_TOTAL")
    private BigDecimal accountTotal;

    /**
     * 内部状态
     */
    @TableField("INNER_STATUS")
    private String innerStatus;

    /**
     * 未达原因平铺
     */
    @TableField("UNRECIVE_RESON")
    private String unreciveReson;

    /**
     * 需关注事项
     */
    @TableField("REMARK")
    private String remark;

    /**
     * ETL_TIME
     */
    @TableField("ETL_TIME")
    private Date etlTime;

    @TableField("LOCK_STATUS")
    private String lockStatus;

    /**
     * 银行方期初余额
     */
    @TableField("BANK_BALANCE_QC")
    private BigDecimal bankBalanceQc;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getPlatCode() {
        return platCode;
    }

    public void setPlatCode(String platCode) {
        this.platCode = platCode;
    }
    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public String getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(String accountsId) {
        this.accountsId = accountsId;
    }
    public String getAccountsName() {
        return accountsName;
    }

    public void setAccountsName(String accountsName) {
        this.accountsName = accountsName;
    }
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getChanal() {
        return chanal;
    }

    public void setChanal(String chanal) {
        this.chanal = chanal;
    }
    public String getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(String accountTime) {
        this.accountTime = accountTime;
    }
    public BigDecimal getEnterBalance() {
        return enterBalance;
    }

    public void setEnterBalance(BigDecimal enterBalance) {
        this.enterBalance = enterBalance;
    }
    public BigDecimal getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(BigDecimal bankBalance) {
        this.bankBalance = bankBalance;
    }
    public BigDecimal getNotReached() {
        return notReached;
    }

    public void setNotReached(BigDecimal notReached) {
        this.notReached = notReached;
    }
    public BigDecimal getAmount1() {
        return amount1;
    }

    public void setAmount1(BigDecimal amount1) {
        this.amount1 = amount1;
    }
    public BigDecimal getAmount2() {
        return amount2;
    }

    public void setAmount2(BigDecimal amount2) {
        this.amount2 = amount2;
    }
    public BigDecimal getAmount3() {
        return amount3;
    }

    public void setAmount3(BigDecimal amount3) {
        this.amount3 = amount3;
    }
    public BigDecimal getAmount4() {
        return amount4;
    }

    public void setAmount4(BigDecimal amount4) {
        this.amount4 = amount4;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }
    public BigDecimal getAccountCommit() {
        return accountCommit;
    }

    public void setAccountCommit(BigDecimal accountCommit) {
        this.accountCommit = accountCommit;
    }
    public BigDecimal getAccountTotal() {
        return accountTotal;
    }

    public void setAccountTotal(BigDecimal accountTotal) {
        this.accountTotal = accountTotal;
    }
    public String getInnerStatus() {
        return innerStatus;
    }

    public void setInnerStatus(String innerStatus) {
        this.innerStatus = innerStatus;
    }
    public String getUnreciveReson() {
        return unreciveReson;
    }

    public void setUnreciveReson(String unreciveReson) {
        this.unreciveReson = unreciveReson;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(Date etlTime) {
        this.etlTime = etlTime;
    }
    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }
    public BigDecimal getBankBalanceQc() {
        return bankBalanceQc;
    }

    public void setBankBalanceQc(BigDecimal bankBalanceQc) {
        this.bankBalanceQc = bankBalanceQc;
    }

    @Override
    public String toString() {
        return "BaBalanceAdjustTotal{" +
            "id=" + id +
            ", platCode=" + platCode +
            ", platName=" + platName +
            ", areaCode=" + areaCode +
            ", areaName=" + areaName +
            ", accountsId=" + accountsId +
            ", accountsName=" + accountsName +
            ", accountId=" + accountId +
            ", accountName=" + accountName +
            ", chanal=" + chanal +
            ", accountTime=" + accountTime +
            ", enterBalance=" + enterBalance +
            ", bankBalance=" + bankBalance +
            ", notReached=" + notReached +
            ", amount1=" + amount1 +
            ", amount2=" + amount2 +
            ", amount3=" + amount3 +
            ", amount4=" + amount4 +
            ", startDate=" + startDate +
            ", accountCommit=" + accountCommit +
            ", accountTotal=" + accountTotal +
            ", innerStatus=" + innerStatus +
            ", unreciveReson=" + unreciveReson +
            ", remark=" + remark +
            ", etlTime=" + etlTime +
            ", lockStatus=" + lockStatus +
            ", bankBalanceQc=" + bankBalanceQc +
        "}";
    }
}
