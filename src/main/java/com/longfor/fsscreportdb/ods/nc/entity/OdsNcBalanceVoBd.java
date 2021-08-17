package com.longfor.fsscreportdb.ods.nc.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * NC外系统查询科目余额表-银行直扣
 * </p>
 *
 * @author chenziyao
 * @since 2021-04-28
 */
@TableName("ODS_NC_BALANCE_VO_BD")
@KeySequence("ODS_NC_BALANCE_VO_BD_S")
public class OdsNcBalanceVoBd implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 科目编码
     */
    @TableField("ACCOUNTCODE")
    private String accountcode;

    /**
     * 科目名称
     */
    @TableField("ACCOUNTNAME")
    private String accountname;

    /**
     * 账簿编码
     */
    @TableField("ACCOUNTINGBOOKCODE")
    private String accountingbookcode;

    /**
     * 账簿名称
     */
    @TableField("ACCOUNTINGBOOKNAME")
    private String accountingbookname;

    /**
     * 期初方向(1贷方，0借方,3平,-1双向)
     */
    @TableField("QCFX")
    private String qcfx;

    /**
     * 期初
     */
    @TableField("QC")
    private String qc;

    /**
     * 本期借
     */
    @TableField("DEBITAMOUNT")
    private String debitamount;

    /**
     * 本期贷
     */
    @TableField("CREDITAMOUNT")
    private String creditamount;

    /**
     * 借方累计
     */
    @TableField("DEBITACCUMAMOUNT")
    private String debitaccumamount;

    /**
     * 贷方累计
     */
    @TableField("CREDITACCUMAMOUNT")
    private String creditaccumamount;

    /**
     * 期末方向(1贷方，0借方,3平,-1双向)
     */
    @TableField("QMFX")
    private String qmfx;

    /**
     * 期末
     */
    @TableField("QM")
    private String qm;

    /**
     * 开始年
     */
    @TableField("BEGINYEAR")
    private String beginyear;

    /**
     * 开始月
     */
    @TableField("BEGINMONTH")
    private String beginmonth;

    /**
     * 终了年
     */
    @TableField("ENDYEAR")
    private String endyear;

    /**
     * 终了月
     */
    @TableField("ENDMONTH")
    private String endmonth;

    /**
     * 入库时间
     */
    @TableField("ETL_TIME")
    private LocalDateTime etlTime;

    /**
     * 落库标识（1是手动）
     */
    @TableField("FLAG")
    private String flag;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getAccountcode() {
        return accountcode;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }
    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }
    public String getAccountingbookcode() {
        return accountingbookcode;
    }

    public void setAccountingbookcode(String accountingbookcode) {
        this.accountingbookcode = accountingbookcode;
    }
    public String getAccountingbookname() {
        return accountingbookname;
    }

    public void setAccountingbookname(String accountingbookname) {
        this.accountingbookname = accountingbookname;
    }
    public String getQcfx() {
        return qcfx;
    }

    public void setQcfx(String qcfx) {
        this.qcfx = qcfx;
    }
    public String getQc() {
        return qc;
    }

    public void setQc(String qc) {
        this.qc = qc;
    }
    public String getDebitamount() {
        return debitamount;
    }

    public void setDebitamount(String debitamount) {
        this.debitamount = debitamount;
    }
    public String getCreditamount() {
        return creditamount;
    }

    public void setCreditamount(String creditamount) {
        this.creditamount = creditamount;
    }
    public String getDebitaccumamount() {
        return debitaccumamount;
    }

    public void setDebitaccumamount(String debitaccumamount) {
        this.debitaccumamount = debitaccumamount;
    }
    public String getCreditaccumamount() {
        return creditaccumamount;
    }

    public void setCreditaccumamount(String creditaccumamount) {
        this.creditaccumamount = creditaccumamount;
    }
    public String getQmfx() {
        return qmfx;
    }

    public void setQmfx(String qmfx) {
        this.qmfx = qmfx;
    }
    public String getQm() {
        return qm;
    }

    public void setQm(String qm) {
        this.qm = qm;
    }
    public String getBeginyear() {
        return beginyear;
    }

    public void setBeginyear(String beginyear) {
        this.beginyear = beginyear;
    }
    public String getBeginmonth() {
        return beginmonth;
    }

    public void setBeginmonth(String beginmonth) {
        this.beginmonth = beginmonth;
    }
    public String getEndyear() {
        return endyear;
    }

    public void setEndyear(String endyear) {
        this.endyear = endyear;
    }
    public String getEndmonth() {
        return endmonth;
    }

    public void setEndmonth(String endmonth) {
        this.endmonth = endmonth;
    }
    public LocalDateTime getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(LocalDateTime etlTime) {
        this.etlTime = etlTime;
    }
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "OdsNcBalanceVoBd{" +
            "id=" + id +
            ", accountcode=" + accountcode +
            ", accountname=" + accountname +
            ", accountingbookcode=" + accountingbookcode +
            ", accountingbookname=" + accountingbookname +
            ", qcfx=" + qcfx +
            ", qc=" + qc +
            ", debitamount=" + debitamount +
            ", creditamount=" + creditamount +
            ", debitaccumamount=" + debitaccumamount +
            ", creditaccumamount=" + creditaccumamount +
            ", qmfx=" + qmfx +
            ", qm=" + qm +
            ", beginyear=" + beginyear +
            ", beginmonth=" + beginmonth +
            ", endyear=" + endyear +
            ", endmonth=" + endmonth +
            ", etlTime=" + etlTime +
            ", flag=" + flag +
        "}";
    }
}
