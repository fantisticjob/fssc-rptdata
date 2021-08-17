package com.longfor.fsscreportdb.ods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 资金自查账户表
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-05
 */
@TableName("ODS_ZJZC_ACCOUNT")
@KeySequence("ODS_ZJZCACCOUNT")
public class OdsZjzcAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

    public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}
    @TableField("URID")
    private String urid;

    /**
     * 组织ID
     */
    @TableField("ORGID")
    private String orgid;

    /**
     * 银行ID
     */
    @TableField("BANKID")
    private String bankid;

    /**
     * 开户银行ID
     */
    @TableField("BANKLOCATIONID")
    private String banklocationid;

    /**
     * 银行账号
     */
    @TableField("ACCOUNTNUMBER")
    private String accountnumber;

    /**
     * 户名
     */
    @TableField("ACCOUNTNAME")
    private String accountname;

    /**
     * 账户性质
     */
    @TableField("ACCOUNTTYPEID")
    private String accounttypeid;

    /**
     * 币种ID
     */
    @TableField("CURRENCYID")
    private String currencyid;

    /**
     * 是否银企直联
     */
    @TableField("ISBANKDIRECT")
    private String isbankdirect;

    /**
     * 账户状态｜1-新建2-开户3-冻结4-销户
     */
    @TableField("ACCOUNTSTATE")
    private String accountstate;

    /**
     * 创建人
     */
    @TableField("CREATEDBY")
    private String createdby;

    /**
     * 创建时间
     */
    @TableField("CREATEDON")
    private String createdon;

    /**
     * 修改人
     */
    @TableField("LASTMODIFIEDBY")
    private String lastmodifiedby;

    /**
     * 修改时间
     */
    @TableField("LASTMODIFIEDON")
    private String lastmodifiedon;

    /**
     * 账户标识
     */
    @TableField("BRIEFNUMBER")
    private String briefnumber;

    /**
     * 直联账户存款类型｜1-活期2-定期3-保证金
     */
    @TableField("DEPOSITTYPE")
    private String deposittype;

    /**
     * 账户分组
     */
    @TableField("ACCOUNTGROUPID")
    private String accountgroupid;

    /**
     * 财务系统总账科目
     */
    @TableField("OTHERGLACCOUNT")
    private String otherglaccount;

    /**
     * 财务系统银行账号
     */
    @TableField("OTHERACCOUNTNUMBER")
    private String otheraccountnumber;

    /**
     * 提现账户
     */
    @TableField("WITHDRAWACCOUNTID")
    private String withdrawaccountid;

    /**
     * 开户申请单ID
     */
    @TableField("ACCOUNTAPPLYID")
    private String accountapplyid;

    /**
     * 开户日期
     */
    @TableField("OPENEDDATE")
    private String openeddate;

    /**
     * 销户日期
     */
    @TableField("CLOSEDDATE")
    private String closeddate;

    /**
     * 导出标识|1-未导出2-已导出4-导出中
     */
    @TableField("OUTPUTSTATE")
    private String outputstate;

    /**
     * 导出人
     */
    @TableField("OUTPUTER")
    private String outputer;

    /**
     * 导出状态确认时间
     */
    @TableField("OUTPUTDATE")
    private String outputdate;

    /**
     * 父账户ID
     */
    @TableField("PARENTACCOUNTID")
    private String parentaccountid;

    /**
     * 是否多币种账户
     */
    @TableField("ISMULTICURRENCY")
    private String ismulticurrency;

    /**
     * 地址
     */
    @TableField("ACCOUNTADDRESS")
    private String accountaddress;

    /**
     * 常驻国家性质|Z00-一般贸易区Z01-保税区Z02-加工区Z03-钻石交易区
     */
    @TableField("COUNTRYTYPE")
    private String countrytype;

    /**
     * 标签
     */
    @TableField("ACCOUNTTAGSRANGE")
    private String accounttagsrange;

    /**
     * 审批状态
     */
    @TableField("APPROVESTATE")
    private String approvestate;

    /**
     * 工程项目
     */
    @TableField("PROJECTITEMID")
    private String projectitemid;

    /**
     * 租户ID
     */
    @TableField("TENANTID")
    private String tenantid;

    /**
     * 执行时间
     */
    @TableField("OP_TIME")
    private String op_time;

    /**
     * 航道标签
     */
    @TableField("hd_flag")
    private String hd_flag;
    
    /**
     * 银行code
     */
    @TableField("bank_code")
    private String bank_code;
    
    /**
     * 银行名称
     */
    @TableField("bank_name")
    private String bank_name;

    /**
     * 执行批次
     */
    @TableField("EXECUTION_ID")
    private String execution_id;
    
    /**
     * 城市名称/地区名称
     */
    @TableField("city_name")
    private String city_name;
    /**
     * 平台名称 
     */
    @TableField("f_arae")
    private String f_arae;
    

    public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getF_arae() {
		return f_arae;
	}

	public void setF_arae(String f_arae) {
		this.f_arae = f_arae;
	}

	public String getHd_flag() {
		return hd_flag;
	}

	public void setHd_flag(String hd_flag) {
		this.hd_flag = hd_flag;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getUrid() {
        return urid;
    }

    public void setUrid(String urid) {
        this.urid = urid;
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
    public String getAccountstate() {
        return accountstate;
    }

    public void setAccountstate(String accountstate) {
        this.accountstate = accountstate;
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
    public String getAccountapplyid() {
        return accountapplyid;
    }

    public void setAccountapplyid(String accountapplyid) {
        this.accountapplyid = accountapplyid;
    }
    public String getOpeneddate() {
        return openeddate;
    }

    public void setOpeneddate(String openeddate) {
        this.openeddate = openeddate;
    }
    public String getCloseddate() {
        return closeddate;
    }

    public void setCloseddate(String closeddate) {
        this.closeddate = closeddate;
    }
    public String getOutputstate() {
        return outputstate;
    }

    public void setOutputstate(String outputstate) {
        this.outputstate = outputstate;
    }
    public String getOutputer() {
        return outputer;
    }

    public void setOutputer(String outputer) {
        this.outputer = outputer;
    }
    public String getOutputdate() {
        return outputdate;
    }

    public void setOutputdate(String outputdate) {
        this.outputdate = outputdate;
    }
    public String getParentaccountid() {
        return parentaccountid;
    }

    public void setParentaccountid(String parentaccountid) {
        this.parentaccountid = parentaccountid;
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
    public String getApprovestate() {
        return approvestate;
    }

    public void setApprovestate(String approvestate) {
        this.approvestate = approvestate;
    }
    public String getProjectitemid() {
        return projectitemid;
    }

    public void setProjectitemid(String projectitemid) {
        this.projectitemid = projectitemid;
    }
    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

	public String getOp_time() {
		return op_time;
	}

	public void setOp_time(String op_time) {
		this.op_time = op_time;
	}

	public String getExecution_id() {
		return execution_id;
	}

	public void setExecution_id(String execution_id) {
		this.execution_id = execution_id;
	}
}
