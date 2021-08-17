package com.longfor.fsscreportdb.ods.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.longfor.fsscreportdb.utils.StringUtil;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 资金自查账户余额表
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-05
 */
@TableName("ODS_ZJZC_ACCOUNT_BALANCE")
@KeySequence("ODS_ZJZC_ACCOUNTBALANCE")
public class OdsZjzcAccountBalance implements Serializable {

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
     * 余额日期
     */
    @TableField("REPORTDATE")
    private String reportdate;

    /**
     * 当前余额
     */
    @TableField("CLOSINGBALANCE")
    private BigDecimal closingbalance;

    /**
     * 冻结余额
     */
    @TableField("BLOCKEDBALANCE")
    private BigDecimal blockedbalance;

    /**
     * 留底金额
     */
    @TableField("RESERVEDBALANCE")
    private BigDecimal reservedbalance;

    /**
     * 企业可用余额
     */
    @TableField("ENTERPRISEBALANCE")
    private BigDecimal enterprisebalance;

    /**
     * 银行可用余额
     */
    @TableField("BANKBALANCE")
    private BigDecimal bankbalance;

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
     * 执行批次
     */
    @TableField("EXECUTION_ID")
    private String execution_id;

    /**
     * 前六个月日均余额
     */
    @TableField("ZHSUMBALANCE")
    private BigDecimal zhsumbalance;
    
    /**
     * 前六个月交易笔数
     */
    @TableField("ZHTRANCOUNT")
    private String zhtrancount;
    
    
    public BigDecimal getZhsumbalance() {
		return zhsumbalance;
	}

	public void setZhsumbalance(BigDecimal zhsumbalance) {
		this.zhsumbalance = StringUtil.getTwoDecimal(zhsumbalance);
	}

	public String getZhtrancount() {
		return zhtrancount;
	}

	public void setZhtrancount(String zhtrancount) {
		this.zhtrancount = zhtrancount;
	}

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
    public String getReportdate() {
        return reportdate;
    }

    public void setReportdate(String reportdate) {
        this.reportdate = reportdate;
    }
    public BigDecimal getClosingbalance() {
        return closingbalance;
    }

    public void setClosingbalance(BigDecimal closingbalance) {
        this.closingbalance = StringUtil.getTwoDecimal(closingbalance);
    }
    public BigDecimal getBlockedbalance() {
        return blockedbalance;
    }

    public void setBlockedbalance(BigDecimal blockedbalance) {
        this.blockedbalance = StringUtil.getTwoDecimal(blockedbalance);
    }
    public BigDecimal getReservedbalance() {
        return reservedbalance;
    }

    public void setReservedbalance(BigDecimal reservedbalance) {
        this.reservedbalance = StringUtil.getTwoDecimal(reservedbalance);
    }
    public BigDecimal getEnterprisebalance() {
        return enterprisebalance;
    }

    public void setEnterprisebalance(BigDecimal enterprisebalance) {
        this.enterprisebalance = StringUtil.getTwoDecimal(enterprisebalance);
    }
    public BigDecimal getBankbalance() {
        return bankbalance;
    }

    public void setBankbalance(BigDecimal bankbalance) {
        this.bankbalance = StringUtil.getTwoDecimal(bankbalance);
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
