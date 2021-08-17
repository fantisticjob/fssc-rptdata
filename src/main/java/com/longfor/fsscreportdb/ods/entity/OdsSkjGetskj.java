package com.longfor.fsscreportdb.ods.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.longfor.fsscreportdb.utils.StringUtil;

import java.io.Serializable;

/**
 * <p>
 * 收款机接口
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-19
 */
@TableName("ODS_SKJ_GETSKJ")
@KeySequence("ODS_SKJ_GETSKJ_S")
public class OdsSkjGetskj implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 项目名称
     */
    private String projname;

    /**
     * 交款日期
     */
    private String getdate;

    /**
     * 缴款人
     */
    private String jkr;

    /**
     * 已收金额
     */
    private BigDecimal amount;

    /**
     * 结算方式
     */
    private String fsettlcode;

    /**
     * 支付方式
     */
    private String getform;

    /**
     * 支付方式名称
     */
    private String getformtext;

    /**
     * 票据类型
     */
    private String invotypeenum;

    /**
     * 票据类型2
     */
    private String invotype;

    /**
     * 票据号码
     */
    private String invono;

    /**
     * 款项类型
     */
    private String itemtype;

    /**
     * 款项名称
     */
    private String itemname;

    /**
     * 款项状态
     */
    private String itemstatus;

    /**
     * 单据类型
     */
    private String vouchtype;

    /**
     * 开票人
     */
    private String kpr;

    /**
     * 收款原因
     */
    private String getreason;

    /**
     * 单据类型
     */
    private String statusenum;

    /**
     * 状态
     */
    private String status;

    /**
     * 收款备注
     */
    private String skmemo;
    
    /**
     * 
     */
    private String cwztname;
    

	public String getCwztname() {
		return cwztname;
	}

	public void setCwztname(String cwztname) {
		this.cwztname = cwztname;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getProjname() {
		return projname;
	}

	public void setProjname(String projname) {
		this.projname = projname;
	}

	public String getGetdate() {
		return getdate;
	}

	public void setGetdate(String getdate) {
		this.getdate = getdate;
	}

	public String getJkr() {
		return jkr;
	}

	public void setJkr(String jkr) {
		this.jkr = jkr;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = StringUtil.getTwoDecimal(amount);;
	}

	public String getFsettlcode() {
		return fsettlcode;
	}

	public void setFsettlcode(String fsettlcode) {
		this.fsettlcode = fsettlcode;
	}

	public String getGetform() {
		return getform;
	}

	public void setGetform(String getform) {
		this.getform = getform;
	}

	public String getGetformtext() {
		return getformtext;
	}

	public void setGetformtext(String getformtext) {
		this.getformtext = getformtext;
	}

	public String getInvotypeenum() {
		return invotypeenum;
	}

	public void setInvotypeenum(String invotypeenum) {
		this.invotypeenum = invotypeenum;
	}

	public String getInvotype() {
		return invotype;
	}

	public void setInvotype(String invotype) {
		this.invotype = invotype;
	}

	public String getInvono() {
		return invono;
	}

	public void setInvono(String invono) {
		this.invono = invono;
	}

	public String getItemtype() {
		return itemtype;
	}

	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getItemstatus() {
		return itemstatus;
	}

	public void setItemstatus(String itemstatus) {
		this.itemstatus = itemstatus;
	}

	public String getVouchtype() {
		return vouchtype;
	}

	public void setVouchtype(String vouchtype) {
		this.vouchtype = vouchtype;
	}

	public String getKpr() {
		return kpr;
	}

	public void setKpr(String kpr) {
		this.kpr = kpr;
	}

	public String getGetreason() {
		return getreason;
	}

	public void setGetreason(String getreason) {
		this.getreason = getreason;
	}

	public String getStatusenum() {
		return statusenum;
	}

	public void setStatusenum(String statusenum) {
		this.statusenum = statusenum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSkmemo() {
		return skmemo;
	}

	public void setSkmemo(String skmemo) {
		this.skmemo = skmemo;
	}
}
