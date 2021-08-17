package com.longfor.fsscreportdb.ods.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.longfor.fsscreportdb.utils.StringUtil;

import java.io.Serializable;

/**
 * <p>
 * 资金自查账户申请表
 * </p>
 *
 * @author chenziyao
 * @since 2020-09-09
 */
@TableName("ODS_ZJZC_ACCOUNTS_APPLIES")
@KeySequence("ODS_ZJZC_ACCOUNTSAPPLIES")
public class OdsZjzcAccountsApplies implements Serializable {

    private static final long serialVersionUID = 1L;

   
    /**
     * 主键id
     */
    private BigDecimal id;

    /**
     * urid
     */
    private String urid;

    /**
     * 申请单号
     */
    private String notecode;

    /**
     * 组织id
     */
    private String orgid;

    /**
     * 银行id
     */
    private String bankid;

    /**
     * 开户银行id
     */
    private String banklocationid;

    /**
     * 银行账号
     */
    private String accountnumber;

    /**
     * 户名
     */
    private String accountname;

    /**
     * 账户性质
     */
    private String accounttypeid;

    /**
     * 币种
     */
    private String currencyid;

    /**
     * 是否银企直联
     */
    private String isbankdirect;

    /**
     * 是否票据虚拟户
     */
    private String isbillvirtual;

    /**
     * 是否现金虚拟户
     */
    private String iscashvirtual;

    /**
     * 维护标识｜1-开户2-变更3-冻结4-解冻6-升级5-销户
     */
    private String maintainflag;

    /**
     * 满额上划金额
     */
    private BigDecimal miniuptransferamount;

    /**
     * 最小划拨金额
     */
    private BigDecimal minitransferamount;

    /**
     * 留底金额
     */
    private BigDecimal reservedbalance;

    /**
     * 取整划拨等级｜1-不取整2-十3-百4-千5-万
     */
    private String integerrate;

    /**
     * 审批状态1-未审批2-已审批3-已拒绝4-审批中
     */
    private String approvestate;

    /**
     * 使用状态1-未使用2-已使用
     */
    private String maintainstate;

    /**
     * 作废状态｜1-未作废2-已作废
     */
    private String cancelstate;

    /**
     * 创建人
     */
    private String createdby;

    /**
     * 创建时间
     */
    private String createdon;

    /**
     * 修改人
     */
    private String lastmodifiedby;

    /**
     * 修改时间
     */
    private String lastmodifiedon;

    /**
     * 修改版本
     */
    private BigDecimal rowversion;

    /**
     * 描述
     */
    private String description;

    /**
     * 留底说明
     */
    private String reservedinstruction;

    /**
     * 账户标识
     */
    private String briefnumber;

    /**
     * 直联账户存款类型｜1-活期2-定期3-保证金
     */
    private String deposittype;

    /**
     * 账户分组
     */
    private String accountgroupid;

    /**
     * 财务系统总账科目
     */
    private String otherglaccount;

    /**
     * 财务系统银行账号
     */
    private String otheraccountnumber;

    /**
     * 提现账户
     */
    private String withdrawaccountid;

    /**
     * 银行账号id
     */
    private String accountid;

    /**
     * 终审日期
     */
    private String finalapprovedate;

    /**
     * 申请原因
     */
    private String applyreason;

    /**
     * 作废原因
     */
    private String cancelreason;

    /**
     * 开户日期
     */
    private String openeddate;

    /**
     * 币种范围
     */
    private String currencyrange;

    /**
     * 是否多币种账户
     */
    private String ismulticurrency;

    /**
     * 地址
     */
    private String accountaddress;

    /**
     * 常驻国家性质|z00-一般贸易区z01-保税区z02-加工区z03-钻石交易区
     */
    private String countrytype;

    /**
     * 标签
     */
    private String accounttagsrange;

    /**
     * 工程项目
     */
    private String projectitemid;

    /**
     * 直联权限范围
     */
    private String directrightrange;

    /**
     * 租户id
     */
    private BigDecimal tenantid;

    /**
     * op_time
     */
    private String op_time;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getUrid() {
		return urid;
	}

	public void setUrid(String urid) {
		this.urid = urid;
	}

	public String getNotecode() {
		return notecode;
	}

	public void setNotecode(String notecode) {
		this.notecode = notecode;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getBanklocationid() {
		return banklocationid;
	}

	public void setBanklocationid(String banklocationid) {
		this.banklocationid = banklocationid;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccounttypeid() {
		return accounttypeid;
	}

	public void setAccounttypeid(String accounttypeid) {
		this.accounttypeid = accounttypeid;
	}

	public String getCurrencyid() {
		return currencyid;
	}

	public void setCurrencyid(String currencyid) {
		this.currencyid = currencyid;
	}

	public String getIsbankdirect() {
		return isbankdirect;
	}

	public void setIsbankdirect(String isbankdirect) {
		this.isbankdirect = isbankdirect;
	}

	public String getIsbillvirtual() {
		return isbillvirtual;
	}

	public void setIsbillvirtual(String isbillvirtual) {
		this.isbillvirtual = isbillvirtual;
	}

	public String getIscashvirtual() {
		return iscashvirtual;
	}

	public void setIscashvirtual(String iscashvirtual) {
		this.iscashvirtual = iscashvirtual;
	}

	public String getMaintainflag() {
		return maintainflag;
	}

	public void setMaintainflag(String maintainflag) {
		this.maintainflag = maintainflag;
	}

	public BigDecimal getMiniuptransferamount() {
		return miniuptransferamount;
	}

	public void setMiniuptransferamount(BigDecimal miniuptransferamount) {
		this.miniuptransferamount = StringUtil.getTwoDecimal(miniuptransferamount);
	}

	public BigDecimal getMinitransferamount() {
		return minitransferamount;
	}

	public void setMinitransferamount(BigDecimal minitransferamount) {
		this.minitransferamount = StringUtil.getTwoDecimal(minitransferamount);
	}

	public BigDecimal getReservedbalance() {
		return reservedbalance;
	}

	public void setReservedbalance(BigDecimal reservedbalance) {
		this.reservedbalance = StringUtil.getTwoDecimal(reservedbalance);
	}

	public String getIntegerrate() {
		return integerrate;
	}

	public void setIntegerrate(String integerrate) {
		this.integerrate = integerrate;
	}

	public String getApprovestate() {
		return approvestate;
	}

	public void setApprovestate(String approvestate) {
		this.approvestate = approvestate;
	}

	public String getMaintainstate() {
		return maintainstate;
	}

	public void setMaintainstate(String maintainstate) {
		this.maintainstate = maintainstate;
	}

	public String getCancelstate() {
		return cancelstate;
	}

	public void setCancelstate(String cancelstate) {
		this.cancelstate = cancelstate;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public String getLastmodifiedby() {
		return lastmodifiedby;
	}

	public void setLastmodifiedby(String lastmodifiedby) {
		this.lastmodifiedby = lastmodifiedby;
	}

	public String getLastmodifiedon() {
		return lastmodifiedon;
	}

	public void setLastmodifiedon(String lastmodifiedon) {
		this.lastmodifiedon = lastmodifiedon;
	}

	public BigDecimal getRowversion() {
		return rowversion;
	}

	public void setRowversion(BigDecimal rowversion) {
		this.rowversion = StringUtil.getTwoDecimal(rowversion);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReservedinstruction() {
		return reservedinstruction;
	}

	public void setReservedinstruction(String reservedinstruction) {
		this.reservedinstruction = reservedinstruction;
	}

	public String getBriefnumber() {
		return briefnumber;
	}

	public void setBriefnumber(String briefnumber) {
		this.briefnumber = briefnumber;
	}

	public String getDeposittype() {
		return deposittype;
	}

	public void setDeposittype(String deposittype) {
		this.deposittype = deposittype;
	}

	public String getAccountgroupid() {
		return accountgroupid;
	}

	public void setAccountgroupid(String accountgroupid) {
		this.accountgroupid = accountgroupid;
	}

	public String getOtherglaccount() {
		return otherglaccount;
	}

	public void setOtherglaccount(String otherglaccount) {
		this.otherglaccount = otherglaccount;
	}

	public String getOtheraccountnumber() {
		return otheraccountnumber;
	}

	public void setOtheraccountnumber(String otheraccountnumber) {
		this.otheraccountnumber = otheraccountnumber;
	}

	public String getWithdrawaccountid() {
		return withdrawaccountid;
	}

	public void setWithdrawaccountid(String withdrawaccountid) {
		this.withdrawaccountid = withdrawaccountid;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getFinalapprovedate() {
		return finalapprovedate;
	}

	public void setFinalapprovedate(String finalapprovedate) {
		this.finalapprovedate = finalapprovedate;
	}

	public String getApplyreason() {
		return applyreason;
	}

	public void setApplyreason(String applyreason) {
		this.applyreason = applyreason;
	}

	public String getCancelreason() {
		return cancelreason;
	}

	public void setCancelreason(String cancelreason) {
		this.cancelreason = cancelreason;
	}

	public String getOpeneddate() {
		return openeddate;
	}

	public void setOpeneddate(String openeddate) {
		this.openeddate = openeddate;
	}

	public String getCurrencyrange() {
		return currencyrange;
	}

	public void setCurrencyrange(String currencyrange) {
		this.currencyrange = currencyrange;
	}

	public String getIsmulticurrency() {
		return ismulticurrency;
	}

	public void setIsmulticurrency(String ismulticurrency) {
		this.ismulticurrency = ismulticurrency;
	}

	public String getAccountaddress() {
		return accountaddress;
	}

	public void setAccountaddress(String accountaddress) {
		this.accountaddress = accountaddress;
	}

	public String getCountrytype() {
		return countrytype;
	}

	public void setCountrytype(String countrytype) {
		this.countrytype = countrytype;
	}

	public String getAccounttagsrange() {
		return accounttagsrange;
	}

	public void setAccounttagsrange(String accounttagsrange) {
		this.accounttagsrange = accounttagsrange;
	}

	public String getProjectitemid() {
		return projectitemid;
	}

	public void setProjectitemid(String projectitemid) {
		this.projectitemid = projectitemid;
	}

	public String getDirectrightrange() {
		return directrightrange;
	}

	public void setDirectrightrange(String directrightrange) {
		this.directrightrange = directrightrange;
	}

	public BigDecimal getTenantid() {
		return tenantid;
	}

	public void setTenantid(BigDecimal tenantid) {
		this.tenantid = StringUtil.getTwoDecimal(tenantid);
	}

	public String getOp_time() {
		return op_time;
	}

	public void setOp_time(String op_time) {
		this.op_time = op_time;
	}
    
}
