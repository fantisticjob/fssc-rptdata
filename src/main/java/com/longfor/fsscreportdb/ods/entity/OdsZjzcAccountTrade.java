package com.longfor.fsscreportdb.ods.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.longfor.fsscreportdb.utils.StringUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 资金自查账户交易表
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-05
 */
@TableName("ODS_ZJZC_ACCOUNT_TRADE")
@KeySequence("ODS_ZJZC_ACCOUNTTRADE")
public class OdsZjzcAccountTrade implements Serializable {
	
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
     * 账户ID
     */
    @TableField("ACCOUNTID")
    private String accountid;

    /**
     * 组织ID
     */
    @TableField("ORGID")
    private String orgid;

    /**
     * 来源｜1-手工录入2-系统生成3-文件导入4-银行报告
     */
    @TableField("SOURCE")
    private String source;

    /**
     * 交易日期
     */
    @TableField("TRADEDATE")
    private String tradedate;

    /**
     * 交易时间
     */
    @TableField("TRADEDATETIME")
    private String tradedatetime;

    /**
     * 起息日期
     */
    @TableField("VALUEDATE")
    private String valuedate;

    /**
     * 交易方向|1-支出2-收入
     */
    @TableField("MONEYWAY")
    private String moneyway;

    /**
     * 金额
     */
    @TableField("AMOUNT")
    private BigDecimal amount;

    /**
     * 当前余额
     */
    @TableField("CURRENTBALANCE")
    private BigDecimal currentbalance;

    /**
     * 导出标识|1-未导出2-已导出4-导出中
     */
    @TableField("OUTPUTSTATE")
    private String outputstate;

    /**
     * 是否已对账
     */
    @TableField("ISRECONCILIATION")
    private String isreconciliation;

    /**
     * 认领状态｜1-未认领2-已认领
     */
    @TableField("CLAIMSTATE")
    private String claimstate;

    /**
     * 资金交易生成状态｜1-未生成2-已生成3-生成失败4-生成中5-部分生成
     */
    @TableField("PAYMENTGENERATESTATE")
    private String paymentgeneratestate;

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
     * 修改版本
     */
    @TableField("ROWVERSION")
    private String rowversion;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 来源ID
     */
    @TableField("SOURCEID")
    private String sourceid;

    /**
     * 对账码
     */
    @TableField("CHECKCODE")
    private String checkcode;

    /**
     * 用途
     */
    @TableField("PURPOSE")
    private String purpose;

    /**
     * 备注
     */
    @TableField("COMMENTS")
    private String comments;

    /**
     * 对方账号
     */
    @TableField("OPPOSITEACCOUNTNUMBER")
    private String oppositeaccountnumber;

    /**
     * 对方户名
     */
    @TableField("OPPOSITEACCOUNTNAME")
    private String oppositeaccountname;

    /**
     * 对方银行
     */
    @TableField("OPPOSITEBANK")
    private String oppositebank;

    /**
     * 票据号
     */
    @TableField("BILLCODE")
    private String billcode;

    /**
     * 票据类型
     */
    @TableField("BILLTYPE")
    private String billtype;

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
     * 对账批号
     */
    @TableField("RECONCILIATIONNO")
    private String reconciliationno;

    /**
     * 认领单位
     */
    @TableField("CLAIMORGID")
    private String claimorgid;

    /**
     * 认领人
     */
    @TableField("CLAIMANT")
    private String claimant;

    /**
     * 认领日期
     */
    @TableField("CLAIMDATE")
    private String claimdate;

    /**
     * 资金交易生成失败原因
     */
    @TableField("PAYMENTGENERATEINFO")
    private String paymentgenerateinfo;

    /**
     * 资金交易生成状态确认日期
     */
    @TableField("PAYMENTGENERATEDATE")
    private String paymentgeneratedate;

    /**
     * 银行流水号
     */
    @TableField("BANKSERIALNUMBER")
    private String bankserialnumber;

    /**
     * 用来控制唯一
     */
    @TableField("TRANSSEQ")
    private String transseq;

    /**
     * 来源单据对象
     */
    @TableField("SOURCENOTEOBJECTID")
    private String sourcenoteobjectid;

    /**
     * 明细流水核准状态|1-未核准 2-已核准 3-核准失败
     */
    @TableField("MATCHBOOKSTATE")
    private String matchbookstate;

    /**
     * 明细流水核准批号
     */
    @TableField("MATCHBOOKBATCHNO")
    private String matchbookbatchno;

    /**
     * 业务类型
     */
    @TableField("BUSTYPE")
    private String bustype;

    /**
     * 商品名称
     */
    @TableField("GOODSNAME")
    private String goodsname;

    /**
     * 优惠金额
     */
    @TableField("BENEFITAMOUNT")
    private BigDecimal benefitamount;

    /**
     * 退款金额
     */
    @TableField("REFUNDAMOUNT")
    private BigDecimal refundamount;

    /**
     * 优惠退款金额
     */
    @TableField("REFUNDBENEFITAMOUNT")
    private BigDecimal refundbenefitamount;

    @TableField("FEE")
    private BigDecimal fee;

    @TableField("FEERATE")
    private BigDecimal feerate;

    /**
     * 是否已退款|1-未退款 2-已退款
     */
    @TableField("ISREFUND")
    private String isrefund;

    /**
     * 租户ID
     */
    @TableField("TENANTID")
    private String tenantid;

    /**
     * 导出详情
     */
    @TableField("OUTPUTINFO")
    private String outputinfo;

    /**
     * 企业识别码
     */
    @TableField("CORPREFID")
    private String corprefid;

    /**
     * 企业流水号
     */
    @TableField("ENTERPRISESERIALNUMBER")
    private String enterpriseserialnumber;

    /**
     * 资金系统单据号
     */
    @TableField("NOTECODE")
    private String notecode;

    /**
     * 认领金额
     */
    @TableField("CLAIMAMOUNT")
    private BigDecimal claimamount;

    /**
     * 分配状态|1-未分配2-已分配
     */
    @TableField("DISTRIBUTIONSTATE")
    private String distributionstate;

    /**
     * 确认状态|1-未确定2-部分确认3-完全确认
     */
    @TableField("CONFIRMSTATE")
    private String confirmstate;

    /**
     * 认领状态|1-未认领2-部分认领3-完全认领
     */
    @TableField("TOTALCLAIMSTATE")
    private String totalclaimstate;

    /**
     * 执行时间
     */
    @TableField("OP_TIME")
    private String op_time;

    /**
     * 执行批次
     */
    @TableField("EXECUTION_ID")
    private String execution_id;

    public String getUrid() {
        return urid;
    }

    public void setUrid(String urid) {
        this.urid = urid;
    }
    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }
    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getTradedate() {
        return tradedate;
    }

    public void setTradedate(String tradedate) {
        this.tradedate = tradedate;
    }
    public String getTradedatetime() {
        return tradedatetime;
    }

    public void setTradedatetime(String tradedatetime) {
        this.tradedatetime = tradedatetime;
    }
    public String getValuedate() {
        return valuedate;
    }

    public void setValuedate(String valuedate) {
        this.valuedate = valuedate;
    }
    public String getMoneyway() {
        return moneyway;
    }

    public void setMoneyway(String moneyway) {
        this.moneyway = moneyway;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = StringUtil.getTwoDecimal(amount);
    }
    public BigDecimal getCurrentbalance() {
        return currentbalance;
    }

    public void setCurrentbalance(BigDecimal currentbalance) {
        this.currentbalance = StringUtil.getTwoDecimal(currentbalance);
    }
    public String getOutputstate() {
        return outputstate;
    }

    public void setOutputstate(String outputstate) {
        this.outputstate = outputstate;
    }
    public String getIsreconciliation() {
        return isreconciliation;
    }

    public void setIsreconciliation(String isreconciliation) {
        this.isreconciliation = isreconciliation;
    }
    public String getClaimstate() {
        return claimstate;
    }

    public void setClaimstate(String claimstate) {
        this.claimstate = claimstate;
    }
    public String getPaymentgeneratestate() {
        return paymentgeneratestate;
    }

    public void setPaymentgeneratestate(String paymentgeneratestate) {
        this.paymentgeneratestate = paymentgeneratestate;
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
    public String getRowversion() {
        return rowversion;
    }

    public void setRowversion(String rowversion) {
        this.rowversion = rowversion;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }
    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getOppositeaccountnumber() {
        return oppositeaccountnumber;
    }

    public void setOppositeaccountnumber(String oppositeaccountnumber) {
        this.oppositeaccountnumber = oppositeaccountnumber;
    }
    public String getOppositeaccountname() {
        return oppositeaccountname;
    }

    public void setOppositeaccountname(String oppositeaccountname) {
        this.oppositeaccountname = oppositeaccountname;
    }
    public String getOppositebank() {
        return oppositebank;
    }

    public void setOppositebank(String oppositebank) {
        this.oppositebank = oppositebank;
    }
    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }
    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
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
    public String getReconciliationno() {
        return reconciliationno;
    }

    public void setReconciliationno(String reconciliationno) {
        this.reconciliationno = reconciliationno;
    }
    public String getClaimorgid() {
        return claimorgid;
    }

    public void setClaimorgid(String claimorgid) {
        this.claimorgid = claimorgid;
    }
    public String getClaimant() {
        return claimant;
    }

    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }
    public String getClaimdate() {
        return claimdate;
    }

    public void setClaimdate(String claimdate) {
        this.claimdate = claimdate;
    }
    public String getPaymentgenerateinfo() {
        return paymentgenerateinfo;
    }

    public void setPaymentgenerateinfo(String paymentgenerateinfo) {
        this.paymentgenerateinfo = paymentgenerateinfo;
    }
    public String getPaymentgeneratedate() {
        return paymentgeneratedate;
    }

    public void setPaymentgeneratedate(String paymentgeneratedate) {
        this.paymentgeneratedate = paymentgeneratedate;
    }
    public String getBankserialnumber() {
        return bankserialnumber;
    }

    public void setBankserialnumber(String bankserialnumber) {
        this.bankserialnumber = bankserialnumber;
    }
    public String getTransseq() {
        return transseq;
    }

    public void setTransseq(String transseq) {
        this.transseq = transseq;
    }
    public String getSourcenoteobjectid() {
        return sourcenoteobjectid;
    }

    public void setSourcenoteobjectid(String sourcenoteobjectid) {
        this.sourcenoteobjectid = sourcenoteobjectid;
    }
    public String getMatchbookstate() {
        return matchbookstate;
    }

    public void setMatchbookstate(String matchbookstate) {
        this.matchbookstate = matchbookstate;
    }
    public String getMatchbookbatchno() {
        return matchbookbatchno;
    }

    public void setMatchbookbatchno(String matchbookbatchno) {
        this.matchbookbatchno = matchbookbatchno;
    }
    public String getBustype() {
        return bustype;
    }

    public void setBustype(String bustype) {
        this.bustype = bustype;
    }
    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }
    public BigDecimal getBenefitamount() {
        return benefitamount;
    }

    public void setBenefitamount(BigDecimal benefitamount) {
        this.benefitamount = StringUtil.getTwoDecimal(benefitamount);
    }
    public BigDecimal getRefundamount() {
        return refundamount;
    }

    public void setRefundamount(BigDecimal refundamount) {
        this.refundamount = StringUtil.getTwoDecimal(refundamount);
    }
    public BigDecimal getRefundbenefitamount() {
        return refundbenefitamount;
    }

    public void setRefundbenefitamount(BigDecimal refundbenefitamount) {
        this.refundbenefitamount = StringUtil.getTwoDecimal(refundbenefitamount);
    }
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = StringUtil.getTwoDecimal(fee);
    }
    public BigDecimal getFeerate() {
        return feerate;
    }

    public void setFeerate(BigDecimal feerate) {
        this.feerate = StringUtil.getTwoDecimal(feerate);
    }
    public String getIsrefund() {
        return isrefund;
    }

    public void setIsrefund(String isrefund) {
        this.isrefund = isrefund;
    }
    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }
    public String getOutputinfo() {
        return outputinfo;
    }

    public void setOutputinfo(String outputinfo) {
        this.outputinfo = outputinfo;
    }
    public String getCorprefid() {
        return corprefid;
    }

    public void setCorprefid(String corprefid) {
        this.corprefid = corprefid;
    }
    public String getEnterpriseserialnumber() {
        return enterpriseserialnumber;
    }

    public void setEnterpriseserialnumber(String enterpriseserialnumber) {
        this.enterpriseserialnumber = enterpriseserialnumber;
    }
    public String getNotecode() {
        return notecode;
    }

    public void setNotecode(String notecode) {
        this.notecode = notecode;
    }
    public BigDecimal getClaimamount() {
        return claimamount;
    }

    public void setClaimamount(BigDecimal claimamount) {
        this.claimamount = StringUtil.getTwoDecimal(claimamount);
    }
    public String getDistributionstate() {
        return distributionstate;
    }

    public void setDistributionstate(String distributionstate) {
        this.distributionstate = distributionstate;
    }
    public String getConfirmstate() {
        return confirmstate;
    }

    public void setConfirmstate(String confirmstate) {
        this.confirmstate = confirmstate;
    }
    public String getTotalclaimstate() {
        return totalclaimstate;
    }

    public void setTotalclaimstate(String totalclaimstate) {
        this.totalclaimstate = totalclaimstate;
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
