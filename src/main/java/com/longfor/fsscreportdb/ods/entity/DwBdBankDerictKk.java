package com.longfor.fsscreportdb.ods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 银行直扣汇总
 * </p>
 *
 *
 *
 * @author chenziyao
 * @since 2020-07-30
 */
@TableName("DW_BD_BANK_DERICT_KK")
@KeySequence(value = "SEQ_DEMO_ID")
public class DwBdBankDerictKk implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 时间
     */
    @TableField("DATA_DATE")
    private String dataDate;

    /**
     * 平台
     */
    @TableField("F_AREA")
    private String fArea;

    /**
     * 地区
     */
    @TableField("F_DQMC")
    private String fDqmc;

    /**
     * 公司
     */
    @TableField("F_COMPANY")
    private String fCompany;

    /**
     * 流水发生额
     */
    @TableField("AMOUNT")
    private BigDecimal amount;

    /**
     * 期末余额
     */
    @TableField("BALANCE")
    private BigDecimal balance;

    /**
     * 流水日期
     */
    @TableField("H_TIME")
    private String hTime;

    /**
     * 提网时间1
     */
    @TableField("TW_DATE1")
    private Date twDate1;

    /**
     * 提网时长1
     */
    @TableField("TW_SC1")
    private BigDecimal twSc1;

    /**
     * 提网金额1
     */
    @TableField("TW_AMOUNT1")
    private BigDecimal twAmount1;

    /**
     * 提网时间2
     */
    @TableField("TW_DATE2")
    private Date twDate2;

    /**
     * 提网时长2
     */
    @TableField("TW_SC2")
    private BigDecimal twSc2;

    /**
     * 提网金额2
     */
    @TableField("TW_AMOUNT2")
    private BigDecimal twAmount2;

    /**
     * 提网时间3
     */
    @TableField("TW_DATE3")
    private Date twDate3;

    /**
     * 提网时长3
     */
    @TableField("TW_SC3")
    private BigDecimal twSc3;

    /**
     * 提网金额3
     */
    @TableField("TW_AMOUNT3")
    private BigDecimal twAmount3;

    /**
     * 提网时间4
     */
    @TableField("TW_DATE4")
    private Date twDate4;

    /**
     * 提网时长4
     */
    @TableField("TW_SC4")
    private BigDecimal twSc4;

    /**
     * 提网金额4
     */
    @TableField("TW_AMOUNT4")
    private BigDecimal twAmount4;

    /**
     * 提网时间5
     */
    @TableField("TW_DATE5")
    private Date twDate5;

    /**
     * 提网时长5
     */
    @TableField("TW_SC5")
    private BigDecimal twSc5;

    /**
     * 提网金额5
     */
    @TableField("TW_AMOUNT5")
    private BigDecimal twAmount5;

    /**
     * 类型
     */
    @TableField("TYPE")
    private String type;

    /**
     * 是否异常数据
     */
    @TableField("IS_EXCEPTION")
    private String isException;

    /**
     * 事项描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 预计处理时间
     */
    @TableField("YJ_DEL_TIME")
    private String yjDelTime;

    /**
     * 说明
     */
    @TableField("SHUO_MING")
    private String shuoMing;

    /**
     * 最长提网时间
     */
    @TableField("MAX_TW_SC")
    private BigDecimal maxTwSc;

    /**
     * 流程是/否完成
     */
    @TableField("IS_FINISHED")
    private String isFinished;

    /**
     * 总账
     */
    @TableField("ZZ")
    private String zz;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }
    public String getfArea() {
        return fArea;
    }

    public void setfArea(String fArea) {
        this.fArea = fArea;
    }
    public String getfDqmc() {
        return fDqmc;
    }

    public void setfDqmc(String fDqmc) {
        this.fDqmc = fDqmc;
    }
    public String getfCompany() {
        return fCompany;
    }

    public void setfCompany(String fCompany) {
        this.fCompany = fCompany;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public String gethTime() {
        return hTime;
    }

    public void sethTime(String hTime) {
        this.hTime = hTime;
    }
    public Date getTwDate1() {
        return twDate1;
    }

    public void setTwDate1(Date twDate1) {
        this.twDate1 = twDate1;
    }
    public BigDecimal getTwSc1() {
        return twSc1;
    }

    public void setTwSc1(BigDecimal twSc1) {
        this.twSc1 = twSc1;
    }
    public BigDecimal getTwAmount1() {
        return twAmount1;
    }

    public void setTwAmount1(BigDecimal twAmount1) {
        this.twAmount1 = twAmount1;
    }
    public Date getTwDate2() {
        return twDate2;
    }

    public void setTwDate2(Date twDate2) {
        this.twDate2 = twDate2;
    }
    public BigDecimal getTwSc2() {
        return twSc2;
    }

    public void setTwSc2(BigDecimal twSc2) {
        this.twSc2 = twSc2;
    }
    public BigDecimal getTwAmount2() {
        return twAmount2;
    }

    public void setTwAmount2(BigDecimal twAmount2) {
        this.twAmount2 = twAmount2;
    }
    public Date getTwDate3() {
        return twDate3;
    }

    public void setTwDate3(Date twDate3) {
        this.twDate3 = twDate3;
    }
    public BigDecimal getTwSc3() {
        return twSc3;
    }

    public void setTwSc3(BigDecimal twSc3) {
        this.twSc3 = twSc3;
    }
    public BigDecimal getTwAmount3() {
        return twAmount3;
    }

    public void setTwAmount3(BigDecimal twAmount3) {
        this.twAmount3 = twAmount3;
    }
    public Date getTwDate4() {
        return twDate4;
    }

    public void setTwDate4(Date twDate4) {
        this.twDate4 = twDate4;
    }
    public BigDecimal getTwSc4() {
        return twSc4;
    }

    public void setTwSc4(BigDecimal twSc4) {
        this.twSc4 = twSc4;
    }
    public BigDecimal getTwAmount4() {
        return twAmount4;
    }

    public void setTwAmount4(BigDecimal twAmount4) {
        this.twAmount4 = twAmount4;
    }
    public Date getTwDate5() {
        return twDate5;
    }

    public void setTwDate5(Date twDate5) {
        this.twDate5 = twDate5;
    }
    public BigDecimal getTwSc5() {
        return twSc5;
    }

    public void setTwSc5(BigDecimal twSc5) {
        this.twSc5 = twSc5;
    }
    public BigDecimal getTwAmount5() {
        return twAmount5;
    }

    public void setTwAmount5(BigDecimal twAmount5) {
        this.twAmount5 = twAmount5;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getIsException() {
        return isException;
    }

    public void setIsException(String isException) {
        this.isException = isException;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getYjDelTime() {
        return yjDelTime;
    }

    public void setYjDelTime(String yjDelTime) {
        this.yjDelTime = yjDelTime;
    }
    public String getShuoMing() {
        return shuoMing;
    }

    public void setShuoMing(String shuoMing) {
        this.shuoMing = shuoMing;
    }
    public BigDecimal getMaxTwSc() {
        return maxTwSc;
    }

    public void setMaxTwSc(BigDecimal maxTwSc) {
        this.maxTwSc = maxTwSc;
    }
    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }
    public String getZz() {
        return zz;
    }

    public void setZz(String zz) {
        this.zz = zz;
    }

    @Override
    public String toString() {
        return "DwBdBankDerictKk{" +
            "id=" + id +
            ", dataDate=" + dataDate +
            ", fArea=" + fArea +
            ", fDqmc=" + fDqmc +
            ", fCompany=" + fCompany +
            ", amount=" + amount +
            ", balance=" + balance +
            ", hTime=" + hTime +
            ", twDate1=" + twDate1 +
            ", twSc1=" + twSc1 +
            ", twAmount1=" + twAmount1 +
            ", twDate2=" + twDate2 +
            ", twSc2=" + twSc2 +
            ", twAmount2=" + twAmount2 +
            ", twDate3=" + twDate3 +
            ", twSc3=" + twSc3 +
            ", twAmount3=" + twAmount3 +
            ", twDate4=" + twDate4 +
            ", twSc4=" + twSc4 +
            ", twAmount4=" + twAmount4 +
            ", twDate5=" + twDate5 +
            ", twSc5=" + twSc5 +
            ", twAmount5=" + twAmount5 +
            ", type=" + type +
            ", isException=" + isException +
            ", description=" + description +
            ", yjDelTime=" + yjDelTime +
            ", shuoMing=" + shuoMing +
            ", maxTwSc=" + maxTwSc +
            ", isFinished=" + isFinished +
            ", zz=" + zz +
        "}";
    }
}
