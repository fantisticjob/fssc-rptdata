package com.longfor.fsscreportdb.ods.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.longfor.fsscreportdb.utils.StringUtil;

/**
 * <p>
 * 资金自查账户扩展表
 * </p>
 *
 * @author chenziyao
 * @since 2020-09-09
 */
@TableName("ODS_ZJZC_ACCOUNTS_EXTEND")
@KeySequence("ODS_ZJZC_ACCOUNTS_EXTEND_S")
public class OdsZjzcAccountsExtend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private BigDecimal id;

    /**
     * 协定存款办理进度 不可办理，已办理，办理中
     */
    private String treatydepositprogress;

    /**
     * 协定存款结息方式 月度结息,季度结息
     */
    private String treatydepositway;

    /**
     * 协定存款协定利率
     */
    private BigDecimal treatydepositrate;

    /**
     * 不可办理协定存款原因
     */
    private String undealdepositreason;
    
    /**
     * 
     * 账户id
     */
    private String account_id;
    
    /**
     * 创建时间
     * 
     */
    private String createdon;
    
    /**
     * 
     * 修改时间
     */
    private String  lastmodifiedon;
    
    
	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

	public String getLastmodifiedon() {
		return lastmodifiedon;
	}

	public void setLastmodifiedon(String lastmodifiedon) {
		this.lastmodifiedon = lastmodifiedon;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getTreatydepositprogress() {
		return treatydepositprogress;
	}

	public void setTreatydepositprogress(String treatydepositprogress) {
		this.treatydepositprogress = treatydepositprogress;
	}

	public String getTreatydepositway() {
		return treatydepositway;
	}

	public void setTreatydepositway(String treatydepositway) {
		this.treatydepositway = treatydepositway;
	}

	public BigDecimal getTreatydepositrate() {
		return treatydepositrate;
	}

	public void setTreatydepositrate(BigDecimal treatydepositrate) {
		this.treatydepositrate = StringUtil.getTwoDecimal(treatydepositrate);
	}

	public String getUndealdepositreason() {
		return undealdepositreason;
	}

	public void setUndealdepositreason(String undealdepositreason) {
		this.undealdepositreason = undealdepositreason;
	}
    
}
